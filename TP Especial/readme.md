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

* GET   ```http://localhost:8080/api/users/alrededores/1```

#### Token

* ejemplo:
```
    {
        "id_token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcEBnbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9BRE1JTixST0xFX01BSU5URU5BTkNFLFJPTEVfVVNFUiIsImV4cCI6MTcwMDA5Njc3N30.HNM_cujayJgqfbqLJdGRPIIqCRI8VF-NkCDNrzdyBFjGmCyVTlELFxQ4d8-J2aF-7vwufsSoa-Xi0usHUqp2nQ"
    }
```

--------------------------------------------------------------------------------------------------------------------

## Funcionalidades:

--------------------------------------------------------------------------------------------------------------------
Registrar monopatín en mantenimiento (debe marcarse como no disponible para su uso)

* POST   ```http://localhost:8080/api/mantenimientos```

#### Request body

```
    {
        "scooter_id":12,
        "scooter_station_id":3
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
Registrar fin de mantenimiento de monopatín

* PUT   ```http://localhost:8080/api/mantenimientos/{id}```

Ejemplo:

* PUT   ```http://localhost:8080/api/mantenimientos/655568a41a12871998c05582```

#### Token

* ejemplo:
```
    {
        "id_token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcEBnbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9BRE1JTixST0xFX01BSU5URU5BTkNFLFJPTEVfVVNFUiIsImV4cCI6MTcwMDA5Njc3N30.HNM_cujayJgqfbqLJdGRPIIqCRI8VF-NkCDNrzdyBFjGmCyVTlELFxQ4d8-J2aF-7vwufsSoa-Xi0usHUqp2nQ"
    }
```

--------------------------------------------------------------------------------------------------------------------
Agregar monopatín

* POST   ```http://localhost:8080/api/monopatines```

#### Request body

```
    {
       "model":"BMW"
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
Quitar monopatín

* DELETE   ```http://localhost:8080/api/monopatines/17```


#### Token

* ejemplo:
```
    {
        "id_token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcEBnbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9BRE1JTixST0xFX01BSU5URU5BTkNFLFJPTEVfVVNFUiIsImV4cCI6MTcwMDA5Njc3N30.HNM_cujayJgqfbqLJdGRPIIqCRI8VF-NkCDNrzdyBFjGmCyVTlELFxQ4d8-J2aF-7vwufsSoa-Xi0usHUqp2nQ"
    }
```

--------------------------------------------------------------------------------------------------------------------
Registrar parada

* POST   ```http://localhost:8080/api/paradas```

#### Request body

```
    {
        "name": "movediza",
        "location": {
                        "latitud": 0.0,
                        "longitud": 0.0
                    },
        "cantMaxSkateboards": 10
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
Quitar parada

* DELETE   ```http://localhost:8080/api/paradas/5```


#### Token

* ejemplo:
```
    {
        "id_token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcEBnbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9BRE1JTixST0xFX01BSU5URU5BTkNFLFJPTEVfVVNFUiIsImV4cCI6MTcwMDA5Njc3N30.HNM_cujayJgqfbqLJdGRPIIqCRI8VF-NkCDNrzdyBFjGmCyVTlELFxQ4d8-J2aF-7vwufsSoa-Xi0usHUqp2nQ"
    }
```

--------------------------------------------------------------------------------------------------------------------
Ubicar monopatín en parada (opcional)

* PUT   ```http://localhost:8080/api/admin/paradas/{station_id}```

Ejemplo:

* PUT   ```http://localhost:8080/api/admin/paradas/4```

#### Request body
* Asegurarnos que la localización del monopatín concida con la de la estación
```
    {
       "id": "16"
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
Definir precio

* PUT   ```http://localhost:8080/api/tarifas/{id}```

Ejemplo:

* PUT   ```http://localhost:8080/api/tarifas/65560eef2837d120b458a62a```

#### Request body

```
    {
        "cost_per_min": 19999.0
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
Definir tarifa extra para reinicio por pausa extensa

* PUT   ```http://localhost:8080/api/tarifas/{id}```

Ejemplo:

* PUT   ```http://localhost:8080/api/tarifas/65560eef2837d120b458a62a```

#### Request body

```
    {
        "extended_pause_cost": 2550.0
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
Generar reporte de uso de monopatines por kilómetros
Generar reporte de uso de monopatines por tiempo con pausas
Generar reporte de uso de monopatines por tiempo sin pausas

* GET   ```http://localhost:8080/api/mantenimientos/reporte-monopatines-por/{campo}```

Ejemplo:

* GET   ```http://localhost:8080/api/mantenimientos/reporte-monopatines-por/kilometros```

#### Token

* ejemplo:
```
    {
        "id_token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcEBnbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9BRE1JTixST0xFX01BSU5URU5BTkNFLFJPTEVfVVNFUiIsImV4cCI6MTcwMDA5Njc3N30.HNM_cujayJgqfbqLJdGRPIIqCRI8VF-NkCDNrzdyBFjGmCyVTlELFxQ4d8-J2aF-7vwufsSoa-Xi0usHUqp2nQ"
    }
```

--------------------------------------------------------------------------------------------------------------------
## ABMs:

--------------------------------------------------------------------------------------------------------------------

algunos abms

--------------------------------------------------------------------------------------------------------------------

## instalar base de datos mongo db

https://www.mongodb.com/try/download/community-kubernetes-operator

https://www.mongodb.com/try/download/shell