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

* GET   ```http://localhost:8080/api/mantenimientos/reporte-monopatines-por-km/con-pausas/{stringBoolean}```

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

* GET   ```http://localhost:8080/api/admin/monopatines/cantidad-de-viajes-mayor-que/1/anio/2023```

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

* PATCH   ```http://localhost:8080/api/mantenimientos/{id}```

Ejemplo:

* PATCH   ```http://localhost:8080/api/mantenimientos/655568a41a12871998c05582```

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
### ABM Viajes:

--------------------------------------------------------------------------------------------------------------------
#### Recuperar todos los viajes

* GET   ```http://localhost:8080/api/viajes```

#### Token

* ejemplo:
```
    {
        "id_token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcEBnbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9BRE1JTixST0xFX01BSU5URU5BTkNFLFJPTEVfVVNFUiIsImV4cCI6MTcwMDA5Njc3N30.HNM_cujayJgqfbqLJdGRPIIqCRI8VF-NkCDNrzdyBFjGmCyVTlELFxQ4d8-J2aF-7vwufsSoa-Xi0usHUqp2nQ"
    }
```


--------------------------------------------------------------------------------------------------------------------
#### Recuperar un viaje por id

* GET   ```http://localhost:8080/api/viajes/{id}```

Ejemplo:

* GET   ```http://localhost:8080/api/viajes/1```

#### Token

* ejemplo:
```
    {
        "id_token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcEBnbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9BRE1JTixST0xFX01BSU5URU5BTkNFLFJPTEVfVVNFUiIsImV4cCI6MTcwMDA5Njc3N30.HNM_cujayJgqfbqLJdGRPIIqCRI8VF-NkCDNrzdyBFjGmCyVTlELFxQ4d8-J2aF-7vwufsSoa-Xi0usHUqp2nQ"
    }
```

--------------------------------------------------------------------------------------------------------------------
#### Crear un viaje

* POST   ```http://localhost:8080/api/viajes```

#### Request body

```
        {
            "scooter": {
                "id": 8,
                "state": "disponible",
                "location": {
                                "latitud": -44.5732,
                                "longitud": -58.4578
                            },
                "kmsTraveled": 0.0,
                "kmsMant": 0.0,
                "numbKmsForMaint": 100.0,
                "totalUsageTime": 0,
                "pausedTime": 0,
                "model": "Mercedes-Benz"
            },
            "idUser": 5,
            "idAccount": 1,
            "initiated": "2022-04-29T18:13:00.000+00:00",
            "finalized": null,
            "kilometersTraveled": 0.0,
            "totalPrice": 0.0,
            "pauseTime": null,
            "activePause": false,
            "id_tarifa": 3,
            "finalStation":1
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

#### Editar un viaje por id

* PUT   ```http://localhost:8080/api/viajes/{id}```

Ejemplo:

* PUT   ```http://localhost:8080/api/viajes/21```

#### Request body

* ejemplo:
```
    {
            "scooter": {
            "id": 8,
            "state": "disponible",
            "location": {
            "latitud": -44.5732,
            "longitud": -58.4578
            },
            "kmsTraveled": 0.0,
            "kmsMant": 0.0,
            "numbKmsForMaint": 100.0,
            "totalUsageTime": 0,
            "pausedTime": 0,
            "model": "Mercedes-Benz"
            },
            "idUser": 5,
            "idAccount": 1,
            "initiated": "2022-04-29T18:13:00.000+00:00",
            "finalized": "2023-04-29T18:13:00.000+00:00",
            "kilometersTraveled": 1000.0,
            "totalPrice": 10.0,
            "pauseTime": null,
            "activePause": false,
            "id_tarifa": 3,
            "finalStation": 1
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
#### Eliminar un viaje por id

* DELETE   ```http://localhost:8080/api/viajes/21```

#### Token

* ejemplo:
```
    {
        "id_token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcEBnbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9BRE1JTixST0xFX01BSU5URU5BTkNFLFJPTEVfVVNFUiIsImV4cCI6MTcwMDA5Njc3N30.HNM_cujayJgqfbqLJdGRPIIqCRI8VF-NkCDNrzdyBFjGmCyVTlELFxQ4d8-J2aF-7vwufsSoa-Xi0usHUqp2nQ"
    }
```

--------------------------------------------------------------------------------------------------------------------
### ABM Tarifas:

--------------------------------------------------------------------------------------------------------------------
#### Recuperar todos las tarifas

* GET   ```http://localhost:8080/api/tarifas```

#### Token

* ejemplo:
```
    {
        "id_token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcEBnbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9BRE1JTixST0xFX01BSU5URU5BTkNFLFJPTEVfVVNFUiIsImV4cCI6MTcwMDA5Njc3N30.HNM_cujayJgqfbqLJdGRPIIqCRI8VF-NkCDNrzdyBFjGmCyVTlELFxQ4d8-J2aF-7vwufsSoa-Xi0usHUqp2nQ"
    }
```


--------------------------------------------------------------------------------------------------------------------
#### Recuperar una tarifa por id

* GET   ```http://localhost:8080/api/tarifas/{id}```

Ejemplo:

* GET   ```http://localhost:8080/api/tarifas/655776d7c869bb604d41af8d```

#### Token

* ejemplo:
```
    {
        "id_token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcEBnbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9BRE1JTixST0xFX01BSU5URU5BTkNFLFJPTEVfVVNFUiIsImV4cCI6MTcwMDA5Njc3N30.HNM_cujayJgqfbqLJdGRPIIqCRI8VF-NkCDNrzdyBFjGmCyVTlELFxQ4d8-J2aF-7vwufsSoa-Xi0usHUqp2nQ"
    }
```

--------------------------------------------------------------------------------------------------------------------
#### Crear una tarifa

* POST   ```http://localhost:8080/api/tarifas```

#### Request body

```
    {
        "name": "Semana santa",
        "cost_per_min": 15.77,
        "extended_pause_cost": 30.80
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

#### Editar una tarifa por id

* PUT   ```http://localhost:8080/api/tarifas/{id}```

Ejemplo:

* PUT   ```http://localhost:8080/api/tarifas/655785ee4658ff0fcad3442e```

#### Request body

* ejemplo:
```
    {
        "name": "Semana1 santa1",
        "cost_per_min": 15.771,
        "extended_pause_cost": 301.80
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
#### Eliminar una tarifa por id

* DELETE   ```http://localhost:8080/api/tarifas/655785ee4658ff0fcad3442e```

#### Token

* ejemplo:
```
    {
        "id_token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcEBnbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9BRE1JTixST0xFX01BSU5URU5BTkNFLFJPTEVfVVNFUiIsImV4cCI6MTcwMDA5Njc3N30.HNM_cujayJgqfbqLJdGRPIIqCRI8VF-NkCDNrzdyBFjGmCyVTlELFxQ4d8-J2aF-7vwufsSoa-Xi0usHUqp2nQ"
    }
```

--------------------------------------------------------------------------------------------------------------------
### ABM Tarifas Programadas:

--------------------------------------------------------------------------------------------------------------------
#### Recuperar todos las tarifas programas

* GET   ```http://localhost:8080/api/tarifas/programadas```

#### Token

* ejemplo:
```
    {
        "id_token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcEBnbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9BRE1JTixST0xFX01BSU5URU5BTkNFLFJPTEVfVVNFUiIsImV4cCI6MTcwMDA5Njc3N30.HNM_cujayJgqfbqLJdGRPIIqCRI8VF-NkCDNrzdyBFjGmCyVTlELFxQ4d8-J2aF-7vwufsSoa-Xi0usHUqp2nQ"
    }
```

--------------------------------------------------------------------------------------------------------------------
#### Crear una tarifa programada

* Ejercicio 3.f ya resuelto más arriba.

--------------------------------------------------------------------------------------------------------------------

#### Editar una tarifa programa por id

* PUT   ```http://localhost:8080/api/tarifas/programadas/{id}```

Ejemplo:

* PUT   ```http://localhost:8080/api/tarifas/programadas/655785ee4658ff0fcad3442e```

#### Request body

* ejemplo:
```
     {
        "fare_to_update_id": "654f6d40267df94bb9d181b8",
        "cost_per_min": 1335,
        "extended_pause_cost": 2445,
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
#### Eliminar una tarifa programada por id

* DELETE   ```http://localhost:8080/api/tarifas/programadas/655788964658ff0fcad3442f```

#### Token

* ejemplo:
```
    {
        "id_token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcEBnbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9BRE1JTixST0xFX01BSU5URU5BTkNFLFJPTEVfVVNFUiIsImV4cCI6MTcwMDA5Njc3N30.HNM_cujayJgqfbqLJdGRPIIqCRI8VF-NkCDNrzdyBFjGmCyVTlELFxQ4d8-J2aF-7vwufsSoa-Xi0usHUqp2nQ"
    }
```

--------------------------------------------------------------------------------------------------------------------
### ABM Monopatin:

--------------------------------------------------------------------------------------------------------------------
#### Recuperar todos los monopatines

* GET   ```http://localhost:8080/api/monopatines```

#### Token

* ejemplo:
```
    {
        "id_token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcEBnbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9BRE1JTixST0xFX01BSU5URU5BTkNFLFJPTEVfVVNFUiIsImV4cCI6MTcwMDA5Njc3N30.HNM_cujayJgqfbqLJdGRPIIqCRI8VF-NkCDNrzdyBFjGmCyVTlELFxQ4d8-J2aF-7vwufsSoa-Xi0usHUqp2nQ"
    }
```

--------------------------------------------------------------------------------------------------------------------
#### Recuperar un monopatin por id

* GET   ```http://localhost:8080/api/monopatines/{id}```

Ejemplo:

* GET   ```http://localhost:8080/api/monopatines/1```

#### Token

* ejemplo:
```
    {
        "id_token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcEBnbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9BRE1JTixST0xFX01BSU5URU5BTkNFLFJPTEVfVVNFUiIsImV4cCI6MTcwMDA5Njc3N30.HNM_cujayJgqfbqLJdGRPIIqCRI8VF-NkCDNrzdyBFjGmCyVTlELFxQ4d8-J2aF-7vwufsSoa-Xi0usHUqp2nQ"
    }
```

--------------------------------------------------------------------------------------------------------------------
#### Crear un monopatin

* Ejercicio resuelto más arriba en la parte de funcionalidades.

--------------------------------------------------------------------------------------------------------------------

#### Editar un monopatin por id

* PUT   ```http://localhost:8080/api/monopatines/{id}```

Ejemplo:

* PUT   ```http://localhost:8080/api/monopatines/1```

#### Request body

* ejemplo:

```
    {
        "state": "en_uso",
        "kmsTraveled": 1020.88,
        "kmsMant": 1.0,
        "totalUsageTime": 1,
        "pausedTime": 1,
        "model": "Moto"
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
#### Eliminar un monopatin por id

* Ejercicio resuelto más arriba en la parte de funcionalidades.

--------------------------------------------------------------------------------------------------------------------
### ABM Paradas:

--------------------------------------------------------------------------------------------------------------------
#### Recuperar todos los paradas

* GET   ```http://localhost:8080/api/paradas```

#### Token

* ejemplo:
```
    {
        "id_token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcEBnbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9BRE1JTixST0xFX01BSU5URU5BTkNFLFJPTEVfVVNFUiIsImV4cCI6MTcwMDA5Njc3N30.HNM_cujayJgqfbqLJdGRPIIqCRI8VF-NkCDNrzdyBFjGmCyVTlELFxQ4d8-J2aF-7vwufsSoa-Xi0usHUqp2nQ"
    }
```

--------------------------------------------------------------------------------------------------------------------
#### Recuperar una parada por id

* GET   ```http://localhost:8080/api/paradas/{id}```

Ejemplo:

* GET   ```http://localhost:8080/api/paradas/1```

#### Token

* ejemplo:
```
    {
        "id_token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcEBnbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9BRE1JTixST0xFX01BSU5URU5BTkNFLFJPTEVfVVNFUiIsImV4cCI6MTcwMDA5Njc3N30.HNM_cujayJgqfbqLJdGRPIIqCRI8VF-NkCDNrzdyBFjGmCyVTlELFxQ4d8-J2aF-7vwufsSoa-Xi0usHUqp2nQ"
    }
```

--------------------------------------------------------------------------------------------------------------------
#### Crear una parada

* Ejercicio resuelto más arriba en la parte de funcionalidades.

--------------------------------------------------------------------------------------------------------------------

#### Editar una parada por id

* PUT   ```http://localhost:8080/api/paradas/{id}```

Ejemplo:

* PUT   ```http://localhost:8080/api/paradas/1```

#### Request body

* ejemplo:

```
{
    "name": "Lastoninas",
    "location": {
        "latitud": -31.6099,
        "longitud": -18.3923
    },
    "cantMaxSkateboards": 20
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
#### Eliminar una parada por id

* Ejercicio resuelto más arriba en la parte de funcionalidades.

--------------------------------------------------------------------------------------------------------------------
### ABM Usuarios:

--------------------------------------------------------------------------------------------------------------------
#### Recuperar todos los usuarios

* GET   ```http://localhost:8080/api/users```

#### Token

* ejemplo:
```
    {
        "id_token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcEBnbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9BRE1JTixST0xFX01BSU5URU5BTkNFLFJPTEVfVVNFUiIsImV4cCI6MTcwMDA5Njc3N30.HNM_cujayJgqfbqLJdGRPIIqCRI8VF-NkCDNrzdyBFjGmCyVTlELFxQ4d8-J2aF-7vwufsSoa-Xi0usHUqp2nQ"
    }
```

--------------------------------------------------------------------------------------------------------------------
#### Recuperar un usuario por id

* GET   ```http://localhost:8080/api/users/{id}```

Ejemplo:

* GET   ```http://localhost:8080/api/users/1```

#### Token

* ejemplo:
```
    {
        "id_token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcEBnbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9BRE1JTixST0xFX01BSU5URU5BTkNFLFJPTEVfVVNFUiIsImV4cCI6MTcwMDA5Njc3N30.HNM_cujayJgqfbqLJdGRPIIqCRI8VF-NkCDNrzdyBFjGmCyVTlELFxQ4d8-J2aF-7vwufsSoa-Xi0usHUqp2nQ"
    }
```

--------------------------------------------------------------------------------------------------------------------
#### Crear un usuario

* Ejercicio resuelto más arriba, corresponde al registro.

--------------------------------------------------------------------------------------------------------------------

#### Editar un usuario por id

* PUT   ```http://localhost:8080/api/users/{id}```

Ejemplo:

* PUT   ```http://localhost:8080/api/users/1```

#### Request body

* ejemplo:

```
    {
        "name": "Martin",
        "surname": "Varela",
        "phone_number": "34845-453074",
        "email": "pp@gmail.com",
        "accounts": [1,3],
        "authorities": ["ROLE_ADMIN","ROLE_MAINTENANCE","ROLE_USER"],
        "password": "123456789"
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
#### Eliminar un usuario por id

* DELETE   ```http://localhost:8080/api/users/1```

#### Token

* ejemplo:
```
    {
        "id_token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcEBnbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9BRE1JTixST0xFX01BSU5URU5BTkNFLFJPTEVfVVNFUiIsImV4cCI6MTcwMDA5Njc3N30.HNM_cujayJgqfbqLJdGRPIIqCRI8VF-NkCDNrzdyBFjGmCyVTlELFxQ4d8-J2aF-7vwufsSoa-Xi0usHUqp2nQ"
    }
```

--------------------------------------------------------------------------------------------------------------------
### ABM Cuentas:

--------------------------------------------------------------------------------------------------------------------
#### Recuperar todos los cuentas

* GET   ```http://localhost:8080/api/accounts```

#### Token

* ejemplo:
```
    {
        "id_token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcEBnbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9BRE1JTixST0xFX01BSU5URU5BTkNFLFJPTEVfVVNFUiIsImV4cCI6MTcwMDA5Njc3N30.HNM_cujayJgqfbqLJdGRPIIqCRI8VF-NkCDNrzdyBFjGmCyVTlELFxQ4d8-J2aF-7vwufsSoa-Xi0usHUqp2nQ"
    }
```

--------------------------------------------------------------------------------------------------------------------
#### Recuperar una cuenta por id

* GET   ```http://localhost:8080/api/accounts/{id}```

Ejemplo:

* GET   ```http://localhost:8080/api/accounts/1```

#### Token

* ejemplo:
```
    {
        "id_token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcEBnbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9BRE1JTixST0xFX01BSU5URU5BTkNFLFJPTEVfVVNFUiIsImV4cCI6MTcwMDA5Njc3N30.HNM_cujayJgqfbqLJdGRPIIqCRI8VF-NkCDNrzdyBFjGmCyVTlELFxQ4d8-J2aF-7vwufsSoa-Xi0usHUqp2nQ"
    }
```

--------------------------------------------------------------------------------------------------------------------
#### Crear una cuenta

* POST   ```http://localhost:8080/api/accounts```

#### Request body

```
    {
        "money": 2000.0,
        "date_of_creation": "2023-11-17T22:39:20.140489500",
        "phone_number": "154-223344",
        "active": true
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

#### Editar una cuenta por id

* PUT   ```http://localhost:8080/api/accounts/{id}```

Ejemplo:

* PUT   ```http://localhost:8080/api/accounts/1```

#### Request body

* ejemplo:

```
   {
        "money": 3000.0,
        "date_of_creation": "2023-10-17T22:39:20.140489500",
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
#### Eliminar una cuenta por id

* DELETE   ```http://localhost:8080/api/accounts/1```

#### Token

* ejemplo:
```
    {
        "id_token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcEBnbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9BRE1JTixST0xFX01BSU5URU5BTkNFLFJPTEVfVVNFUiIsImV4cCI6MTcwMDA5Njc3N30.HNM_cujayJgqfbqLJdGRPIIqCRI8VF-NkCDNrzdyBFjGmCyVTlELFxQ4d8-J2aF-7vwufsSoa-Xi0usHUqp2nQ"
    }
```

--------------------------------------------------------------------------------------------------------------------
### ABM Mantenimientos:

--------------------------------------------------------------------------------------------------------------------
#### Recuperar todos los mantenimientos

* GET   ```http://localhost:8080/api/mantenimientos```

#### Token

* ejemplo:
```
    {
        "id_token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcEBnbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9BRE1JTixST0xFX01BSU5URU5BTkNFLFJPTEVfVVNFUiIsImV4cCI6MTcwMDA5Njc3N30.HNM_cujayJgqfbqLJdGRPIIqCRI8VF-NkCDNrzdyBFjGmCyVTlELFxQ4d8-J2aF-7vwufsSoa-Xi0usHUqp2nQ"
    }
```

--------------------------------------------------------------------------------------------------------------------
#### Recuperar una cuenta por id

* GET   ```http://localhost:8080/api/mantenimientos/{id}```

Ejemplo:

* GET   ```http://localhost:8080/api/mantenimientos/655776d7c869bb604d41af90```

#### Token

* ejemplo:
```
    {
        "id_token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcEBnbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9BRE1JTixST0xFX01BSU5URU5BTkNFLFJPTEVfVVNFUiIsImV4cCI6MTcwMDA5Njc3N30.HNM_cujayJgqfbqLJdGRPIIqCRI8VF-NkCDNrzdyBFjGmCyVTlELFxQ4d8-J2aF-7vwufsSoa-Xi0usHUqp2nQ"
    }
```

--------------------------------------------------------------------------------------------------------------------
#### Crear un mantenimiento

* Ejercicio resuelto más arriba en la parte de funcionalidades.

--------------------------------------------------------------------------------------------------------------------

#### Editar un mantenimiento por id

* PUT   ```http://localhost:8080/api/mantenimientos/{id}```

Ejemplo:

* PUT   ```http://localhost:8080/api/mantenimientos/655776d7c869bb604d41af90```

#### Request body

* ejemplo:

```
   {
        "scooter_id": 12,
        "scooter_station_id": 3,
        "start_date": "2000-11-17T22:39:20.140489500",
        "end_date": "2021-11-17T22:39:30.638081"
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
#### Eliminar un mantenimiento por id

* DELETE   ```http://localhost:8080/api/mantenimientos/655776d7c869bb604d41af90```

#### Token

* ejemplo:
```
    {
        "id_token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcEBnbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9BRE1JTixST0xFX01BSU5URU5BTkNFLFJPTEVfVVNFUiIsImV4cCI6MTcwMDA5Njc3N30.HNM_cujayJgqfbqLJdGRPIIqCRI8VF-NkCDNrzdyBFjGmCyVTlELFxQ4d8-J2aF-7vwufsSoa-Xi0usHUqp2nQ"
    }
```

--------------------------------------------------------------------------------------------------------------------


## instalar base de datos mongo db

https://www.mongodb.com/try/download/community-kubernetes-operator

https://www.mongodb.com/try/download/shell