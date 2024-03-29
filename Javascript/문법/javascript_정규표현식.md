# Javascript 정규표현식

## 구성
**/패턴/플래그**
- `/` 시작기호와  `/` 종료기호 사이
- 플래그
  - 고급 검색을 위한 전역 옵션
  - i : 대소문자를 구별하지 않고 검색
  - g : 문자열 내의 모든 패턴을 검색
    - g가 없으면 최초 검색 결과 하나만 반환
    - g가 있으면 패턴에 일치하는 결과 모두 배열로 반환

## 정규식 패턴 기호
`a-zA-Z` : 영어 알파벳 소문자&대문자 전체
`ㄱ-ㅎ가-힣` : 한글 문자 
`0-9` : 숫자
`.` : 모든 문자열 (숫자,한글,영어,특수기호,공백 모두), 줄바꿈x
`\d` : 숫자전체
`\D` : 숫자가 아닌 것
`\w` : 밑줄 문자를 포함한 영숫자 문자 `a-zA-Z0-9_` 와 동일.
`\W` : `\w`가 아닌 것
`\s` : space 공백
`\S` : 공백 제외 나머지
`\특수기호` : \? \# 등등

## 정규식 검색 기준 패턴
`|` : 또는. `a|b`
`[]` : or 처리 묶음. ex) `[a-z]` : a ~ z 중 포함
`[^]` : ^뒤에 제외. ex) `[^abc]` : a,b,c 문자 제외
`^문자열` : 해당 문자열로 시작. ex) `/^www/` www로 시작
`문자열$` : 해당 문자열로 끝. ex) `/com$/` com으로 끝 

## 정규식 갯수 반복 패턴 
`?` : 없거나 최대 한개만. ex) `/abc?/` 
`*` : 없거나 있거나
`+` : 최소 한개 or 여러개

## 정규식 그룹 패턴
`()` : 그룹화 및 복사
`(?:)`  : 그룹화(복사x)


## 출처
https://inpa.tistory.com/entry/JS-%F0%9F%93%9A-%EC%A0%95%EA%B7%9C%EC%8B%9D-RegExp-%EB%88%84%EA%B5%AC%EB%82%98-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0-%EC%89%BD%EA%B2%8C-%EC%A0%95%EB%A6%AC

https://regexr.com/