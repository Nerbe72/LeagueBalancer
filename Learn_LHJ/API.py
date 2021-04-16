import requests  # api를 불러오는 모듈
import pandas as pd
from collections import defaultdict
import time  # sleep 함수를 사용하기 위함 / 좋지 않으므로 대체제 필요

REGION = "kr"


def set_url(query, req, option=''):
    assembled = "https://kr.api.riotgames.com/lol/" + query + req + '?api_key=' + api_key + option
    return assembled


api_key = "이 부분에 api입력"

name = input("소환사명 : ")
season = str(13)

try:
    summoner = set_url("summoner/v4/summoners/by-name/", name)
    res = requests.get(summoner)
    account = res.json()['accountId']
    print('Encrypted Account ID : ', account)
except:
    print("소환사 데이터 획득 오류")

try:
    match = set_url("match/v4/matchlists/by-account/", account, '&queue=420&endIndex=30')  # queue=430&
    res = requests.get(match)
    temp = pd.DataFrame(res.json()['matches'])
    temp = temp[temp['lane'] != 'NONE']
    match_info = pd.DataFrame(temp.loc[:, ['gameId', 'lane']]).reset_index(drop=True)
    print(match_info)
    MainLane = match_info['lane'].value_counts()[0:2]
    print('Game count : ', len(match_info))
    print("MostLane : ", end='')
    for i in range(2):
        print(MainLane.index[i], end=' ')
    print()
except:
    print("매칭 데이터 획득 오류")

game_info = pd.DataFrame()
game_id = match_info['gameId'].values.tolist()

print(id)

games = defaultdict(int)
id = 0
for i in game_id:
    game = set_url("match/v4/matches/", str(i))
    res = requests.get(game)
    game_temp = res.json()

    try:
        ids = game_temp['participantIdentities']
        for i in ids:
            if (i['player']['summonerName'] == name):
                id = i['participantId']
                print(id)
    except:
        print('인게임 플레이어 ID 호출 오류')

    games[game_temp['gameId']] = game_temp['participants'][id-1]['stats']

games = pd.DataFrame(games)
games = games.transpose()
games = games.reset_index(drop=True)
games = games.loc[:, ['kills', 'deaths', 'assists']]
games['kda'] = (games['kills'] + games['assists']) / games['deaths']

print(games)

