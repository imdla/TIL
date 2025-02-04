# 딕셔너리
  # 자료를 쌍으로 저장할 때
my_dict = {'name': 'ken', 'age': 20, 'license': True}

# get 메서드
print(my_dict['name'])
# print(my_dict['address']) KeyError 발생
print(my_dict.get('name'))
print(my_dict.get('address', 0)) # None 반환 or 반환할 대체 값 설정

# keys, values, items 메서드
print(my_dict.keys())
print(my_dict.values())
print(my_dict.items())


# 집합
  # 그 외 검색이 잦을 때
  # 단순히 중복 제거할 때 등등
my_set = set()

# 원소 삽입
my_set.add(5)
print(my_set)

# 원소 삭제
# discard() : 없는 키 삭제 -> 에러 발생하지 않음
# my_set.discard(5)
# print(my_set)

my_set.remove(5)
print(my_set)

# remove() : 없는 키 삭제 -> KeyError 발생
my_set.remove(3)