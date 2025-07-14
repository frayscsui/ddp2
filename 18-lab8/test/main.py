# from pprint import pprint

with open("test/gender.txt") as f:
    print(", ".join([f'"{t}"' for t in f.read().splitlines()]))