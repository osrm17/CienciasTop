## Uso 

Ingresamos como super usuario a psql
```text
sudo -u postgres psql
```

Dentro de psql creamos el usuario **ctop** y modificamos su contrasenia
```text
postgres=# create user ctop superuser createdb createrole inherit login replication;
...
postgres=# alter user ctop with password 'ctop';
```

Se implementa la BD 

```text
psql -U ctop -d postgres -a -f DDL.sql
```

Verificar la implementacion 

```text
psql -U ctop -d postgres 
...
postgres=# \dt
```

Se pobla la BD 

```text
psql -U ctop -d postgres -a -f DML.sql
```

Verificar la poblacion

```text
psql -U ctop -d postgres 
...
postgres=# select * from ctop.usuario;
```

Definir los procedimientos almacenados
```text
psql -U ctop -d postgres -a -f Functions.sql
```