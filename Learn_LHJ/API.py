import requests #api를 불러오는 모듈
import pandas as pd
import gc #가비지 컬렉터
import json
import time #sleep 함수를 사용하기 위함 / 좋지 않으므로 대체제 필요

REGION = "kr"

def set_URL(query, req, region=REGION):
    assembled = "https://"+region+".api.riotgames.com/lol/"+query+req+'?api_key='+api_key
    return assembled


api_key = "RGAPI-1d071ed1-8235-416a-87af-7f6a34b5c04c"

name = input("소환사명 : ")
season = str(13)


try:
    summoner = set_URL("summoner/v4/summoners/by-name/", name)
    res = requests.get(summoner)
    accountid = res.json()['accountId']
except:
    print("소환사 데이터 획득 오류")

try:
    match = set_URL("match/v4/matchlists/by-account/", accountid)
    res = requests.get(match)
    temp = pd.DataFrame(res.json()['matches'])
    match_info = pd.DataFrame(temp.loc[((temp['queue'] == 420) ^ (temp['queue'] == 430)), ['gameId', 'lane']]).reset_index()
    print(match_info)
    MainLane = match_info['lane'].value_counts()[0:2]
    print(MainLane)
    print(MainLane.index.name)
    print("MostLane : ")
except:
    print("매칭 데이터 획득 오류")

try:
    for i in match_info.index:
        matchid = match_info.get_value(i, 'gameId')
        print(matchid)
        game = set_URL("match/v4/matches/", matchid)
        print(game)
        res = requests.get(game)
except:
    print("통계 획득 오류")





