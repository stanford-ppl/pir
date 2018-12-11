import pandas as pd
from pandautil import *
from collections import OrderedDict

# from https://docs.google.com/spreadsheets/d/1ib4jPlyKifF4ALXWo8rhwFOWV6EBJF7sJ32UU5CRf_Y/edit?usp=sharing
scale_28_to_45 = 1/0.47436959
cu_area = { "pcu": 0.849, "pmu": 0.532 } # mm^2
AREA_HEADER='synthesis area (um^2)'
ENERGY_HEADER='flit energy (J)'

class PlasticineModel:
    def __init__(self, path, tech):
        self.path = path
        self.tech = tech
        self.routertb = self.loadrouter()
        self.switchtb = self.loadswitch()

    def loadrouter(self):
        # index = "sim_scalar,packet_rate,buffer_size,flit_data_width,num_message_classes,num_nodes_per_router"
        # index = index.split(",")
        tab = pd.read_csv("{}/router{}.csv".format(self.path, self.tech), header=0,
            encoding="utf-8-sig",
            # index_col=index
        )
        interp_tab = pd.read_csv("{}/router{}_interp.csv".format(self.path, self.tech), header=0,
            encoding="utf-8-sig",
            # index_col=index
        )
        tab = pd.concat([tab, interp_tab], axis=0)
        old = pd.read_csv("data/old/router28.csv", header=0,encoding="utf-8-sig")
        tab = pd.concat([tab, lookup(old, flit_data_width=512, num_message_classes=8, drop=False)], axis=0,
                sort=True)
        tab['vc_buffer_size'] = tab['buffer_size'] / tab['num_message_classes']
        return tab
    
    def loadswitch(self):
        # index = "sim_scalar,packet_rate,XBAR_FULL,LINKS_SW,DIRS_SW,WIDTH,BACKPRESSURE,DIRS_CU,LINKS_CU"
        # index = index.split(",")
        # tab = pd.read_csv("{}/switch{}_merged.csv".format(self.path, self.tech), header=0,
            # encoding="utf-8-sig",
            # # index_col=index
        # )
        tab = pd.read_csv("data/switch28.csv", header=0, encoding="utf-8-sig")
        tab = tab.assign(sim_scalar=0)
        old = pd.read_csv("data/old/switch28.csv", header=0,encoding="utf-8-sig")
        new = pd.read_csv("data/switch28_new.csv", header=0,encoding="utf-8-sig")
        tab = pd.concat([tab, lookup(old, WIDTH=512, drop=False)], axis=0, sort=True)
        tab = pd.concat([tab, lookup(old, WIDTH=32, LINKS_SW=1, drop=False)], axis=0, sort=True)
        tab = pd.concat([tab, new], axis=0, sort=True)
        return tab

    def get_router_spec(self, **conf):
        vc = conf['vcLimit']
        tab = lookup(self.routertb, 
                num_message_classes=vc,
                flit_data_width=512, 
                buffer_size=vc * 3, 
                **conf
                )
    
        return tab
    
    def get_switch_spec(self, header, **conf):
        conf['sim_scalar'] = 0
        conf['XBAR_FULL'] = 1
        conf['DIRS_CU'] = 1
        link_prop = conf['link_prop'] if 'link_prop' in conf else 'B'
        conf['BACKPRESSURE']=(1 if link_prop == 'B' else 0)
        if conf["WIDTH"] == 512:
            conf['LINKS_SW'] = conf["vlink"]
        elif conf["WIDTH"] == 32:
            conf['LINKS_SW'] = conf["slink"]
        if conf['LINKS_SW'] == 0:
            return 0
        res = get_col_value(lookup(self.switchtb, **conf), header)
        return res

    def hasDynamic(self, **conf):
        return conf["vcLimit"] is not None and conf["vcLimit"] > 0

    def getLayout(self, conf):
        conf["n1_node"] = 0
        conf["n2_node"] = 0
        if "row" in conf and conf["row"] is not None and "col" in conf and conf["col"] is not None and not conf["p2p"]:
            conf["n1_node"] = conf["row"] * conf["col"] - 1
            conf["n2_node"] = conf["row"] * 2 + 1
        return

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
    
    # unit in um^2
    def get_net_area_summary(self, conf):
        conf['total_vswitch_area'] = 0
        conf['total_sswitch_area'] = 0
        conf['total_router_area'] = 0
        conf['total_net_area'] = 0
        conf['n1_vswitch_area'] = 0
        conf['n2_vswitch_area'] = 0
        conf['n1_sswitch_area'] = 0
        conf['n2_sswitch_area'] = 0
        conf['n1_router_area'] = 0
        conf['n2_router_area'] = 0
        self.getLayout(conf)
        if conf['p2p'] or conf['asic']:
            return
        conf['n1_vswitch_area'] = self.get_switch_spec(AREA_HEADER, LINKS_CU=4, WIDTH=512, **conf)
        # conf['n2_vswitch_area'] = get_col_value(self.get_switch_spec(LINKS_CU=8, WIDTH=512, **conf), AREA_HEADER)
        # TODO
        conf['n2_vswitch_area'] = self.get_switch_spec(AREA_HEADER, LINKS_CU=4, WIDTH=512, **conf)
        conf['n1_sswitch_area'] = self.get_switch_spec(AREA_HEADER, LINKS_CU=4, WIDTH=32, **conf)
        conf['n2_sswitch_area'] = self.get_switch_spec(AREA_HEADER, LINKS_CU=8, WIDTH=32, **conf)
        conf['total_vswitch_area'] = \
            conf['n1_node'] * conf['n1_vswitch_area'] + conf["n2_node"] * conf["n2_vswitch_area"]
        conf['total_sswitch_area'] = \
            conf['n1_node'] * conf['n1_sswitch_area'] + conf["n2_node"] * conf["n2_sswitch_area"]

        if self.hasDynamic(**conf) :
            conf['n1_router_area'] = get_col_value(self.get_router_spec(sim_scalar=0,
                num_nodes_per_router=1, **conf), AREA_HEADER)
            conf['n2_router_area'] = get_col_value(self.get_router_spec(sim_scalar=0,
                num_nodes_per_router=2, **conf), AREA_HEADER)
            conf['total_router_area'] += conf["n1_node"] * conf['n1_router_area']
            conf['total_router_area'] += conf["n2_node"] * conf['n2_router_area']
        conf['pcu_unit_area'] = self.get_cu_area("pcu", **conf)
        conf['pmu_unit_area'] = self.get_cu_area("pmu", **conf)
        conf['total_net_area'] += conf['total_vswitch_area']
        conf['total_net_area'] += conf['total_sswitch_area']
        conf['total_net_area'] += conf['total_router_area']
        return
    
    # unit in J
    def get_net_energy_summary(self, conf):
        size = len(conf)
        conf['total_vswitch_energy'] = 0
        conf['total_sswitch_energy'] = 0
        conf['total_router_energy'] = 0
        conf['total_net_energy'] = 0
        conf['n1_vswitch_energy'] = 0
        conf['n1_sswitch_energy'] = 0
        conf['n1_router_energy'] = 0
        conf['n1_router_scalar_energy'] = 0
        if conf['p2p'] or conf['asic']: return
        conf['n1_vswitch_energy'] = self.get_switch_spec(ENERGY_HEADER, LINKS_CU=4, WIDTH=512,
                **conf)
        conf['n1_sswitch_energy'] = self.get_switch_spec(ENERGY_HEADER, LINKS_CU=4, WIDTH=32,
                **conf)
        conf['total_vswitch_energy'] = int(conf['StatHopsVec']) * conf['n1_vswitch_energy']
        conf['total_sswitch_energy'] = int(conf['StatHopsScal']) * conf['n1_sswitch_energy']
        if self.hasDynamic(**conf) :
            conf['n1_router_energy'] = get_col_value(self.get_router_spec(sim_scalar=0,
                num_nodes_per_router=1, **conf), ENERGY_HEADER)
            conf['n1_router_scalar_energy'] = get_col_value(self.get_router_spec(sim_scalar=1,
                num_nodes_per_router=1, **conf), ENERGY_HEADER)
        conf['total_router_energy'] = int(conf['DynHopsVec']) * conf['n1_router_energy'] 
        conf['total_router_energy'] += int(conf['DynHopsScal']) * conf['n1_router_scalar_energy']
        conf['total_net_energy'] = sum([conf[e] for e in ['total_router_energy',
            'total_vswitch_energy', 'total_sswitch_energy']])
        return

    # unit in W
    def get_power_summary(self, conf):
        if conf['cycle'] is None:
            return
        for k in conf.keys(): 
            if 'total' in k and 'energy' in k: 
                conf[k.replace('energy', 'power')] = float(conf[k] * conf['freq']) / conf['cycle']
        return
