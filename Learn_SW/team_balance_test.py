from random import*
from itertools import*
class Player:
    def __init__(self, rating,num):
        self.num = num
        self.rating = rating
        grav = [1, 0.8, 0.9, 1.1, 1.2]
        self.top = choice(grav)*rating
        self.mid = choice(grav)*rating
        self.jungle = choice(grav)*rating
        self.adcarry = choice(grav)*rating
        self.supporter = choice(grav)*rating
class Team:
    def __init__(self, lst):
        self.top = lst[0].top
        self.mid = lst[1].mid
        self.jungle = lst[2].jungle
        self.adcarry = lst[3].adcarry
        self.supporter = lst[4].supporter
        self.tot = self.top + self.mid + self.jungle + self.adcarry + self.supporter



summoner = list()
num=0
rating = [1000, 1100, 1200, 1300, 1800, 1500, 1400, 1450, 1350, 1300]
position = ["top","mid","jungle","adcarry","supporter"]
rating.sort()
for i in rating:
    summoner.append(Player(i, num))
    num=num+1
    
"""for i in summoner: #레이팅 값 입력 확인용
    print(i.rating)"""

SS = summoner

pd = list(combinations(SS,5))

for k in pd:
    pd2 = list(product(k,position))
    
    
