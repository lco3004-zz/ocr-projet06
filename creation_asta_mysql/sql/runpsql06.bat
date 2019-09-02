@echo off
REM Copyright (c) 2012-2019, EnterpriseDB Corporation.  All rights reserved

REM PostgreSQL server psql runner script for Windows

SET server=localhost
SET /P server="Server [%server%]: "

SET database=db_projet06
SET /P database="Database [%database%]: "

SET port=5432
SET /P port="Port [%port%]: "

SET password=projet06
SET /P password="Password [%password%]: "

SET username=rl_projet06
SET /P username="Username [%username%]: "

for /f "delims=" %%a in ('chcp ^|find /c "932"') do @ SET CLIENTENCODING_JP=%%a
if "%CLIENTENCODING_JP%"=="1" SET PGCLIENTENCODING=SJIS
if "%CLIENTENCODING_JP%"=="1" SET /P PGCLIENTENCODING="Client Encoding [%PGCLIENTENCODING%]: "

REM Run psql
REM "C:\Users\cordier\Documents\leD\ProgramFiles\PostgreSql\bin\psql.exe" -h %server% -U %username% -d %database% -p %port%
"C:\Users\cordier\Documents\leD\ProgramFiles\PostgreSql\bin\psql.exe"  -h %server% -U %username% -d %database% -p %port% 

pause


