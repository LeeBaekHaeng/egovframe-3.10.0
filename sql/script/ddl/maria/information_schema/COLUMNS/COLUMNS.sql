select
    A.*
from
    information_schema.COLUMNS A
where 1 = 1
--     and TABLE_SCHEMA = 'information_schema'
    and TABLE_SCHEMA = 'com'
;

/*

컬럼정의서

[별지 11] 컬럼정의서(공공기관의 데이터베이스 표준화 지침).hwp

테이블 영문명
컬럼 영문명
컬럼 한글명
컬럼 설명
연관 엔터티명
연관 속성명
데이터 타입
데이터 길이
데이터 형식

Not Null 여부
PK정보
FK정보
AK정보
제약조건
개인정보여부
암호화
여부
공개/비공개여부

테이블 영문명 컬럼 영문명  컬럼 한글명  컬럼 설명   연관 엔터티명 연관 속성명  데이터 타입  데이터 길이  데이터 형식  Not Null 여부 PK정보    FK정보    AK정보    제약조건    개인정보여부  암호화여부   공개/비공개여부

*/

select
    A.TABLE_NAME as "테이블 영문명", /* 해당 컬럼이 소속된 테이블의 이름(영문명)을 기재 */
    A.COLUMN_NAME as "컬럼 영문명", /* 컬럼의 물리적 영문 이름으로 ‘표준용어정의서’에 등록된 용어를 사용 */
    A.COLUMN_COMMENT as "컬럼 한글명", /* 해당 컬럼과 연관되는 속성의 이름과 동일하게 부여하며, 표준용어를 준수하여 부여할 컬럼 한글 명칭을 기재 */
    A.COLUMN_COMMENT as "컬럼 설명", /* 컬럼에 대한 이해를 돕기 위하여 필요한 부가적인 설명 및 예외 사항 등을 기재 */
    A.COLUMN_COMMENT as "연관 엔터티명", /* 해당 컬럼이 표현하는 논리적 데이터요소인 ‘엔터티명’을 기재 */
    A.COLUMN_COMMENT as "연관 속성명", /* 해당 컬럼이 표현하는 논리적 데이터요소인 ‘속성명’을 기재 */
    A.DATA_TYPE as "데이터 타입", /* 컬럼 값의 물리적 표현 방식으로 DBMS 종속적인 데이터타입의 이름을 기재 */
    A.CHARACTER_MAXIMUM_LENGTH as "데이터 길이", /* ‘도메인정의서’의 ‘길이’를 기재 */
    'yyyy-mm-dd' as "데이터 형식", /* 컬럼 값의 데이터 형식(혹은 샘플값)을 기재 */
-- 
    case when A.IS_NULLABLE = 'YES' then 'Y' else 'N' end as "Not Null 여부", /* 데이터가 생성(Insert)되는 시점에 컬럼 값이 반드시 존재해야 하는지를 표시 */
    case when A.COLUMN_KEY = 'PRI' then concat('PK', lpad(A.ORDINAL_POSITION, 2, '0')) else NULL end as "PK정보", /* PK(기본키)에 참여하는 컬럼이면 "PK"와 숫자로 된 참여순서를 이용하여 표시하고 PK에 참여하지 않으면 생략 */
    '' as "FK정보", /* AK(부키)에 참여하는 컬럼이 면 "AK"와 숫자로 된 참여순서를 이용하여 표시하고 AK에 참여하지 않으면 생략 */
--     case when A.COLUMN_KEY = 'PRI' then concat('AK_1-', lpad(A.ORDINAL_POSITION, 2, '0')) else NULL end as "AK정보", /* 해당 컬럼이 FK(외래키) 제약에 참여하는 컬럼인 경우에 한해, 관련 테이블 이름과 컬럼 이름을 마침표(.)로 연결하여 기재 */
    NULL as "AK정보", /* 해당 컬럼이 FK(외래키) 제약에 참여하는 컬럼인 경우에 한해, 관련 테이블 이름과 컬럼 이름을 마침표(.)로 연결하여 기재 */
    A.COLUMN_DEFAULT as "제약조건", /* 해당 값 영역에서 설명하는 컬럼 값의 특성 이외에, 컬럼에 대해 추가적으로 명시해야 할 제약조건(허용범위, 구분값, 기본값 등)을 기재 */
    'N' as "개인정보여부", /* 컬럼 값의 개인정보(“개인정보 비식별 조치 가이드라인” 식별자 조치 기준) 포함 여부 */
    'N' as "암호화여부", /* 해당 컬럼이 개인정보 보호 등의 이유로 암호화가 되어 있는지를 기재 */
    '공개' as "공개/비공개여부" /* 해당 컬럼의 메타데이터 및 원천데이터 정보에 대한 공개 또는 비공개 여부(비공개의 경우 비공개 사유 기재) */
-- 
--     A.*
from
    information_schema.COLUMNS A
where 1 = 1
--     and TABLE_SCHEMA = 'information_schema'
    and TABLE_SCHEMA = 'com'
;
