import sys
def output(name):
    for i in name:
        print("{0}".format(i))

def main(argv):
    name = argv[1:]
    output(name)
    
main(sys.argv)
