import angr
import claripy #the solver engine
from angrutils import *
import sys # for stdin

sym_arg_size = 30
sym_arg = claripy.BVS('sym_arg', 8*sym_arg_size)

proj = angr.Project("./target")
argv = [proj.filename]
argv.append(sym_arg)

state = proj.factory.entry_state(args=argv)
simgr = proj.factory.simulation_manager(state)

avoid_addr = [0x4018b1, 0x4018ea]
find_addr  = 0x401060
simgr.explore(find=find_addr, avoid=avoid_addr)

found = simgr.found[0]
flag = found.solver.eval(sym_arg, cast_to=bytes).strip(b"\x00")
print(flag)