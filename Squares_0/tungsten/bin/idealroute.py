import argparse
import csv

def main():
    parser = argparse.ArgumentParser(description='Generate ideal placement')
    parser.add_argument('-l', '--link', type=str, help='input path to link.csv')
    parser.add_argument('-p', '--place', type=str, help='output path to placement file')
    parser.add_argument('-i', '--idealnet', type=str, default='/idealnet', help='full path of idealnet module')

    (opts, args) = parser.parse_known_args()
    with open(opts.link, 'r') as f:
        with open(opts.place, 'w') as wf:
            csv_reader = csv.reader(f)
            next(csv_reader, None)  # skip the headers
            srcid = 0
            dstid = 0
            for row in csv_reader:
                if len(row) == 0: continue
                wf.write('set {} name {}\n'.format(opts.idealnet, row[0].split("/")[-1]))
                wf.write('set {} src {}\n'.format(opts.idealnet, srcid))
                wf.write('set {} addr {}\n'.format(row[0], srcid))
                wf.write('set {} flow {}\n'.format(row[0], 0))
                wf.write('set {} net {}\n'.format(row[0], "ideal"))
                srcid += 1
                for i,val in enumerate(row):
                    if i == 0: continue
                    if val != '' and val is not None:
                        wf.write('set {} name {}\n'.format(opts.idealnet, val.split("/")[-1]))
                        wf.write('set {} dst {}\n'.format(opts.idealnet, dstid))
                        wf.write('set {} addr {}\n'.format(val, dstid))
                        wf.write('set {} flow {}\n'.format(val, 0))
                        wf.write('set {} net {}\n'.format(val, "ideal"))
                        dstid += 1
            wf.write("build\n")
            wf.write("disable net statnet\n")

main()
