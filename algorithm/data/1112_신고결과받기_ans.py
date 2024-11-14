def solution(id_list, report, k):
    report_dict = {key: set() for key in id_list}       # 신고받은 사람 기준(중복 제거 위해 set 자료구조 활용)
    mail_dict = {key: 0 for key in id_list}             # 신고한 사람 기준(결과값 정리 위해)
    
    for report_item in report:                          # 신고 내용 하나씩 꺼내서
        reporter, reported = report_item.split()        
        report_dict[reported].add(reporter)             # {신고받은 사람: set(신고한 사람들)} 로 구조화

    for each_id in id_list:                             # 한명씩 검토
        if len(report_dict[each_id]) >= k:              # k번 이상 신고받았으면
            for key in report_dict[each_id]:            # 해당 인원 신고한 사람들에게
                mail_dict[key] += 1                     # 메일 한통씩 발송

    answer = list(mail_dict.values())                   # 결과는 mail_dict의 value값
    return answer