# Trabajo Práctico Especial: Aplicación para Monopatines Eléctricos

--------------------------------------------------------------------------------------------------------------------

## Registro y Autenticación:

--------------------------------------------------------------------------------------------------------------------
### Registro

* POST   ```http://localhost:8080/api/auth/register```

#### Request body

```
    {
        "name": "Pedro",
        "surname": "Perez",
        "phone_number": "34845-453074",
        "email": "pp@gmail.com",
        "accounts": [1,3],
        "authorities": ["ROLE_ADMIN","ROLE_MAINTENANCE","ROLE_USER"],
        "password": "123456789"
    }
```

--------------------------------------------------------------------------------------------------------------------
### Autenticación

* POST   ```http://localhost:8080/api/auth/authenticate```

#### Request body

```
    {
      "email": "pp@gmail.com",
      "password": "123456789"
    }
```

--------------------------------------------------------------------------------------------------------------------

## Servicios/reportes:

--------------------------------------------------------------------------------------------------------------------
a) . Como encargado de mantenimiento quiero poder generar un reporte de uso de monopatines por kilómetros para establecer si un monopatín requiere de mantenimiento. Este reporte debe poder configurarse para incluir (o no) los tiempos de pausa.

* GET   ```http://localhost:8080/api/mantenimiento/reporte-monopatines-por-km/con-pausas/{stringBoolean}```

Ejemplo:

* GET   ```http://localhost:8080/api/mantenimientos/reporte-monopatines-por-km/con-pausas/true```

#### Token

* ejemplo:
```
    {
        "id_token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcEBnbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9BRE1JTixST0xFX01BSU5URU5BTkNFLFJPTEVfVVNFUiIsImV4cCI6MTcwMDA5Njc3N30.HNM_cujayJgqfbqLJdGRPIIqCRI8VF-NkCDNrzdyBFjGmCyVTlELFxQ4d8-J2aF-7vwufsSoa-Xi0usHUqp2nQ"
    }
```

-------------------------------------------------------------------------------------------------------------------- 
b) . Como administrador quiero poder anular cuentas para inhabilitar el uso momentáneo de la misma.

* PUT   ```http://localhost:8080/api/admin/cuentas/{id}/estado-cuenta```

Ejemplo:

* PUT   ```http://localhost:8080/api/admin/cuentas/1/estado-cuenta```

#### Request body

```
    {
        "active": false
    }
```

#### Token

* ejemplo:
```
    {
        "id_token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcEBnbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9BRE1JTixST0xFX01BSU5URU5BTkNFLFJPTEVfVVNFUiIsImV4cCI6MTcwMDA5Njc3N30.HNM_cujayJgqfbqLJdGRPIIqCRI8VF-NkCDNrzdyBFjGmCyVTlELFxQ4d8-J2aF-7vwufsSoa-Xi0usHUqp2nQ"
    }
```

--------------------------------------------------------------------------------------------------------------------
c) . Como administrador quiero consultar los monopatines con más de X viajes en un cierto año.

* GET   ```http://localhost:8080/api/admin/monopatines/cantidad-de-viajes-mayor-que/{trip_qty}/anio/{year}```

Ejemplo:

* GET   ```http://localhost:8080/api/admin/monopatines/cantidad-de-viajes-mayor-que/1/anio/2001```

#### Token

* ejemplo:
```
    {
        "id_token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcEBnbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9BRE1JTixST0xFX01BSU5URU5BTkNFLFJPTEVfVVNFUiIsImV4cCI6MTcwMDA5Njc3N30.HNM_cujayJgqfbqLJdGRPIIqCRI8VF-NkCDNrzdyBFjGmCyVTlELFxQ4d8-J2aF-7vwufsSoa-Xi0usHUqp2nQ"
    }
```

--------------------------------------------------------------------------------------------------------------------
d) . Como administrador quiero consultar el total facturado en un rango de meses de cierto año.


* GET   ```http://localhost:8080/api/admin/viajes/dinero-total-ganado-en/anio/{year}/desde-mes/{start}/hasta-mes/{end}```

Ejemplo:

* GET   ```http://localhost:8080/api/admin/viajes/dinero-total-ganado-en/anio/2023/desde-mes/1/hasta-mes/12```

#### Token

* ejemplo:
```
    {
        "id_token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcEBnbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9BRE1JTixST0xFX01BSU5URU5BTkNFLFJPTEVfVVNFUiIsImV4cCI6MTcwMDA5Njc3N30.HNM_cujayJgqfbqLJdGRPIIqCRI8VF-NkCDNrzdyBFjGmCyVTlELFxQ4d8-J2aF-7vwufsSoa-Xi0usHUqp2nQ"
    }
```

--------------------------------------------------------------------------------------------------------------------
e) . Como administrador quiero consultar la cantidad de monopatines actualmente en operación, versus la cantidad de monopatines actualmente en mantenimiento.


* GET   ```http://localhost:8080/api/admin/monopatines/cantidad-en-operacion-y-mantenimiento```

#### Token

* ejemplo:
```
    {
        "id_token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcEBnbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9BRE1JTixST0xFX01BSU5URU5BTkNFLFJPTEVfVVNFUiIsImV4cCI6MTcwMDA5Njc3N30.HNM_cujayJgqfbqLJdGRPIIqCRI8VF-NkCDNrzdyBFjGmCyVTlELFxQ4d8-J2aF-7vwufsSoa-Xi0usHUqp2nQ"
    }
```

--------------------------------------------------------------------------------------------------------------------
f) . Como administrador quiero hacer un ajuste de precios, y que a partir de cierta fecha el sistema habilite los nuevos precios.

* POST   ```http://localhost:8080/api/tarifas/programadas```

#### Request body

```
   {
    "fare_to_update_id": "654f6d40267df94bb9d181b8",
    "cost_per_min": 133,
    "extended_pause_cost": 244,
    "scheduled_date": "2023-11-10 11:12:00.0"
    }
```

#### Token

* ejemplo:
```
    {
        "id_token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcEBnbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9BRE1JTixST0xFX01BSU5URU5BTkNFLFJPTEVfVVNFUiIsImV4cCI6MTcwMDA5Njc3N30.HNM_cujayJgqfbqLJdGRPIIqCRI8VF-NkCDNrzdyBFjGmCyVTlELFxQ4d8-J2aF-7vwufsSoa-Xi0usHUqp2nQ"
    }
```

--------------------------------------------------------------------------------------------------------------------
g). Como usuario quiero un listado de los monopatines cercanos a mi zona, para poder encontrar un monopatín cerca de mi ubicación

* GET   ```http://localhost:8080/api/users/alrededores/{id}```

Ejemplo:

* POST   ```http://localhost:8080/api/users/alrededores/1```

#### Token

* ejemplo:
```
    {
        "id_token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcEBnbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9BRE1JTixST0xFX01BSU5URU5BTkNFLFJPTEVfVVNFUiIsImV4cCI6MTcwMDA5Njc3N30.HNM_cujayJgqfbqLJdGRPIIqCRI8VF-NkCDNrzdyBFjGmCyVTlELFxQ4d8-J2aF-7vwufsSoa-Xi0usHUqp2nQ"
    }
```

--------------------------------------------------------------------------------------------------------------------



--------------------------------------------------------------------------------------------------------------------


## instalar base de datos mongo db

https://www.mongodb.com/try/download/community-kubernetes-operator

https://www.mongodb.com/try/download/shell