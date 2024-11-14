def solution(id_list, report, k):
    answer = []
    
    # 유저 id별 유저가 신고한 id
    # user_id에서 key의 value가 set이기 때문에 자동으로 반복 신고는 걸러짐
    user_id = {user: set() for user in id_list}
    for persons in report:
        reporter = list(persons.split(" "))[0]
        respondent = list(persons.split(" "))[1]
        user_id[reporter].add(respondent)
    
    #유저 id별 신고당한 횟수
    respondents_lst = list()
    for respondents in user_id.values():
        respondents_lst.extend(respondents)
    
    respondent_dict = dict.fromkeys(id_list,0)
    for respondent in respondents_lst:
        respondent_dict[respondent] = respondent_dict.get(respondent, 0) +1
    
    # k번 이상 신고당한 id
    stopped_id = []
    for name, report_nums in respondent_dict.items():
        if report_nums >= k:
            stopped_id.append(name)
            
    
    # 유저 id별 유저가 신고한 id에서 정지된 id 추출
    for user, respondents in user_id.items():
        cnt = 0
        for respondent in respondents:
            if respondent in stopped_id:
                cnt += 1
        answer.append(cnt)
    print(answer)         
    
        
    return answer