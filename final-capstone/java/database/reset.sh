#!/bin/bash
BASEDIR=$(dirname $0)
DATABASE=final_capstone

psql -U postgres -d $DATABASE -f "$BASEDIR/drop tables.sql" &&
psql -U postgres -d $DATABASE -f "$BASEDIR/tables.sql" && 
psql -U postgres -d $DATABASE -f "$BASEDIR/test data.sql" &&
psql -U postgres -d $DATABASE -f "$BASEDIR/user.sql"