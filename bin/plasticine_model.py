import pandas as pd
from pandautil import *
from collections import OrderedDict

# from https://docs.google.com/spreadsheets/d/1ib4jPlyKifF4ALXWo8rhwFOWV6EBJF7sJ32UU5CRf_Y/edit?usp=sharing
scale_28_to_45 = 1/0.47436959
cu_area = { "pcu": 0.849, "pmu": 0.532 } # mm^2
cu_power = { "pcu":224.0, "pmu":300.3135, "dag":31.4111 } # mW
D_v1_s4_q4      = { "vlink":1, "slink":4, 'net':'dynamic', 'flit-width':512, 'psim-q':4}
D_v1_s4_q8      = { "vlink":1, "slink":4, 'net':'dynamic', 'flit-width':512, 'psim-q':8}
D_v1_s4_q16 = { "vlink":1, "slink":4, 'net':'dynamic', 'flit-width':512, 'psim-q':16}
AREA_HEADER='synthesis area (um^2)'
ENERGY_HEADER='flit energy (J)'

class PlasticineModel:
    def __init__(self, dataPath, tech):
        self.tech = tech
        self.routertb = self.loadrouter(dataPath)
        self.switchtb = self.loadswitch(dataPath)

    def loadrouter(self, path):
        index = "sim_scalar,packet_rate,buffer_size,flit_data_width,num_message_classes,num_nodes_per_router"
        index = index.split(",")
        tab = pd.read_csv("{}/router{}.csv".format(path, self.tech), header=0,
            encoding="utf-8-sig",
            index_col=index
        )
        return tab
    
    def loadswitch(self, path):
        index = "sim_scalar,packet_rate,XBAR_FULL,LINKS_SW,DIRS_SW,WIDTH,BACKPRESSURE,DIRS_CU,LINKS_CU"
        index = index.split(",")
        tab = pd.read_csv("{}/switch{}.csv".format(path, self.tech), header=0,
            encoding="utf-8-sig",
            index_col=index
        )
        return tab

    def get_router_spec(self, **conf):
        if conf['net'] == 'dynamic':
            if all([D_v1_s4_q4[k] == conf[k] for k in D_v1_s4_q4]):
                return 50577,0
            elif all([D_v1_s4_q8[k] == conf[k] for k in D_v1_s4_q8]):
                return 47275,0
            elif all([D_v1_s4_q16[k] == conf[k] for k in D_v1_s4_q16]):
                return 45624,0
            vc = conf['max_vc']
            if vc <= 2:
                vc = 2
            elif vc <= 4:
                vc = 4
            elif vc <= 8:
                vc = 8
            if conf['vec']:
                sim_scalar=0
            else:
                sim_scalar=1
            flit_width = 512 if 'flit-width' not in conf else conf['flit-width']
            tab = lookup(self.routertb, num_message_classes=vc,
                    num_nodes_per_router=1, flit_data_width=flit_width, buffer_size=vc * 4, sim_scalar=sim_scalar)
    
            area = get_col_value(tab, AREA_HEADER)
            energy = get_col_value(tab, ENERGY_HEADER)
            return area, energy
        else: 
            return 0,0
    
    def get_switch_spec(self, **conf):
        if conf['vec']:
            link = conf['vlink'] if 'vlink' in conf else 0
        else:
            link = conf['slink'] if 'slink' in conf else 0
        if link==0:
            return 0,0
        tab = lookup(self.switchtb,sim_scalar=0,XBAR_FULL=1)
        link_prop = conf['link-prop'] if 'link-prop' in conf else 'db'
        tab = lookup(tab,BACKPRESSURE=(1 if link_prop == 'db' else 0))
        # if conf['net'] == 'dynamic':
            # tab = lookup(tab,DIRS_CU=1,LINKS_CU=4)
        # else:
            # tab = lookup(tab,DIRS_CU=4,LINKS_CU=1)
        tab = lookup(tab,DIRS_CU=1,LINKS_CU=4)
        WIDTH = 512 if conf['vec'] else 32
        tab = lookup(tab, LINKS_SW=link, WIDTH=WIDTH)
        return get_col_value(tab,AREA_HEADER), get_col_value(tab,ENERGY_HEADER)
    
    # unit in um^2
    def get_cu_area(self, cutype, **conf):
        area = cu_area[cutype] * 1e6
        if self.tech == 45:
            area = area * scale_28_to_45
        return area

    # unit in W
    def get_cu_power(self, cutype, **conf):
        power = cu_power[cutype] / 1e3
        if self.tech == 45:
            print("only have power data for 28nm for cus")
            assert(False)
        return power

    # unit in J
    def get_cu_energy(self, cutype, **conf):
        power = self.get_cu_power(cutype, **conf)
        time_per_cycle = 1.0 / conf['freq']
        return power * time_per_cycle

    # unit in um^2
    def get_area_summary(self, **conf):
        summary = OrderedDict()
        summary['router_unit_area'] = self.get_router_spec(vec=True, **conf)[0]
        summary['vswitch_unit_area'] = self.get_switch_spec(vec=True, **conf)[0]
        summary['sswitch_unit_area'] = self.get_switch_spec(vec=False, **conf)[0]
        summary['pcu_unit_area'] = self.get_cu_area("pcu", **conf)
        summary['pmu_unit_area'] = self.get_cu_area("pmu", **conf)
        summary['total_router_area'] = conf['nRouter'] * self.get_router_spec(vec=True, **conf)[0]
        summary['total_vswitch_area'] = conf['nSwitch'] * self.get_switch_spec(vec=True, **conf)[0]
        summary['total_sswitch_area'] = conf['nSwitch'] * self.get_switch_spec(vec=False, **conf)[0]
        summary['total_pcu_area'] = summary['pcu_unit_area'] * conf['nPCU']
        summary['total_pmu_area'] = summary['pmu_unit_area'] * conf['nPMU']
        summary['total_net_area'] = sum([summary[t] for t in ['total_router_area',
            'total_vswitch_area', 'total_sswitch_area']])
        summary['total_area'] = sum([summary[t] for t in ['total_net_area', 'total_pcu_area',
            'total_pmu_area']])
        return summary
    
    # unit in J
    def get_net_energy_summary(self, **conf):
        summary = OrderedDict()
        summary['router_flit_energy'] = self.get_router_spec(vec=True, **conf)[1]
        summary['router_flit_energy_scalar'] = self.get_router_spec(vec=False, **conf)[1]
        summary['vswitch_flit_energy'] = self.get_switch_spec(vec=True, **conf)[1]
        summary['sswitch_flit_energy'] = self.get_switch_spec(vec=False, **conf)[1]
        summary['total_router_energy'] = conf['DynHopsVec'] * summary['router_flit_energy'] 
        summary['total_router_energy'] += conf['DynHopsScal'] * summary['router_flit_energy_scalar']
        summary['total_vswitch_energy'] = conf['StatHopsVec'] * summary['vswitch_flit_energy']
        summary['total_sswitch_energy'] = conf['StatHopsScal'] * summary['sswitch_flit_energy']
        summary['total_net_energy'] = sum([summary[e] for e in ['total_router_energy',
            'total_vswitch_energy', 'total_sswitch_energy']])
        return summary

    # unit in J
    def get_energy_summary(self, **conf):
        summary = self.get_net_energy_summary(**conf)
        summary["pcu_unit_energy"] = self.get_cu_energy("pcu", **conf)
        summary["pmu_unit_energy"] = self.get_cu_energy("pmu", **conf)
        summary["dag_unit_energy"] = self.get_cu_energy("dag", **conf)
        summary["total_pcu_energy"] = conf["pcu_total_active"] * summary["pcu_unit_energy"]
        summary["total_pmu_energy"] = conf["pmu_total_active"] * summary["pmu_unit_energy"]
        summary["total_dag_energy"] = conf["dag_total_active"] * summary["dag_unit_energy"]
        summary['total_energy'] = sum([summary[e] for e in ['total_net_energy',
            'total_pcu_energy', 'total_pmu_energy']])
        return summary

    # unit in W
    def get_power_summary(self, energy, **conf):
        summary = OrderedDict()
        for k in energy: 
            if 'total' in k: summary[k.replace('energy', 'power')] = energy[k] * conf['freq'] / conf['cycle']
        return summary
