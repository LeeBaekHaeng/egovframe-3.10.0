select
    A.*
from
    information_schema.ALL_PLUGINS A
;

select
    A.*
from
    information_schema.VIEWS A
where 1 = 1
--     and A.TABLE_CATALOG = 'def'
-- 
--     and A.TABLE_SCHEMA = 'mysql'
--     and A.TABLE_SCHEMA = 'sys'
-- 
--     and TABLE_NAME = 'VIEWS'
;

select A.TABLE_CATALOG, count(*) cnt from information_schema.VIEWS A group by TABLE_CATALOG
;

select A.TABLE_SCHEMA, count(*) cnt from information_schema.VIEWS A group by TABLE_SCHEMA
;
