import pandas as pd

def lookup(tab, **kws):
    if len(kws)==0:
        return tab
    levels = []
    labels = []
    for k in kws:
        if k not in tab.index.names:
            continue
        levels.append(k)
        labels.append(kws[k])
    try:
        ftab = tab.xs(labels, level=levels)
    except KeyError as e:
        kws = ''
        for level,label in zip(levels,labels):
            kws += ' {}:{}'.format(level, label)
        print('Cannot find kws in the table. kws: {}'.format(kws))
        print('table:')
        print(tab)
        raise e
    return ftab

# get single value in table
def get_col_value(tab, column, **kws):
    tab = lookup(tab, **kws)
    values = set(tab[column].values)
    if len(values) != 1:
        print(column, kws)
        print(tab)
        assert(False)
    return list(values)[0]

# get values in table
def get_col(tab, column, **kws):
    tab = lookup(tab, **kws)
    return tab[column].values

def get_idx_value(tab, index):
    return tab.index.unique(index).values

def get_idx(tab, index):
    return tab.index.get_level_values(index)

