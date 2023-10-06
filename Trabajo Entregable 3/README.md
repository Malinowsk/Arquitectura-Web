[![Postman](https://img.shields.io/badge/Postman-Testing-brightgreen.svg)](https://www.postman.com/)
# Trabajo Entregable 3

 ## 3) Testear la invocación a los servicios REST mediante Postman, o cliente similar.
   - **2a-** [Dar de alta un Estudiante](#dar-de-alta-un-estudiante)
   - **2b-** [Matricular un estudiante en una carrera](#matricular-un-estudiante-en-una-carrera)
   - **2c-** [Recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.](#recuperar-todos-los-estudiantes)
   - **2d-** [Recuperar un estudiante, en base a su número de libreta universitaria.](#recuperar-un-estudiante-por-numero-de-libreta-universitaria)
   - **2e-** [Recuperar estudiantes en base a su genero](#recuperar-estudiantes-por-genero)
   - **2f-** [Recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos](#recuperar-carreras-con-estudiantes-inscriptos)
   - **2g-** [Recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.](#recuperar-estudiantes-de-una-carrera-por-ciudad-de-residencia)
   - **2h-** [Generar un reporte de las carreras, que para cada carrera incluya información de los inscriptos y egresados por año. Se deben ordenar las carreras alfabéticamente, y presentar los años de manera cronológica.](#generar-un-reporte-de-carreras)
  
  A continuacion mostraremos la resolucion del pounto 3, teniendo en cuenta los incisos de la pregunta 2 utilizando Postman para los servicios REST.

### Dar de alta un estudiante
Para dar de alta un estudiante en el sistema, realizamos una solicitud POST a la URL ```  http://localhost:8080/students ```.

 En el cuerpo de la solicitud, proporcionamos los detalles del estudiante, como DNI, nombre, apellido, nacimiento, genero y . Luego, analizamos la respuesta para confirmar que el estudiante se ha registrado correctamente.
```
{   
    "documentNumber":32498791,
    "name": "Mario",
    "surname":  "Casas",
    "birthdate":"1990-10-06T15:18:45.011+00:00",
    "gender":"m",
    "city": "tandil"
}
```  
Y obtendremos como resultado:

![image](https://github.com/Malinowsk/Arquitectura-Web/assets/70240593/1a2ee223-bb39-4a3d-8ebb-cd87b667cce6)

Con la insercion del estudiante en la tabla students:

![image](https://github.com/Malinowsk/Arquitectura-Web/assets/70240593/f663b279-3239-4a8a-804a-d2b6217478a6)

    
--------------------------------------------------------------------------------------------------------------------
### Matricular un estudiante en una carrera
Para matricular un estudiante en una carrera, utilizamos una solicitud POST a la URL ```http://localhost:8080/inscriptions```.

En el cuerpo de la solicitud, especificamos el ID de la carrera en la que deseamos matricularlo, el numero de libreta universitaria (id del alumno) y la fecha de ingreso. 
```
{   
  "career_id":2,
  "student_notebook_number":8,
  "fecha_ingreso": "2023-10-06T15:18:45.011+00:00"
}
```  
Verificamos la respuesta para asegurarnos de que la matriculación se haya realizado con éxito, y obtendremos como resultado:

![image](https://github.com/Malinowsk/Arquitectura-Web/assets/70240593/bf11699c-5d25-44ea-8909-d10a61d739d6)


Con la insercion del estudiante en la tabla students:

![image](https://github.com/Malinowsk/Arquitectura-Web/assets/70240593/5bdb4456-b1d8-4481-8900-aee143d1330b)

--------------------------------------------------------------------------------------------------------------------
### Recuperar todos los estudiantes
* Para recuperar todos los estudiantes registrados en el sistema, realizamos una solicitud GET a la URL ```http://localhost:8080/students```.
  ![image](https://github.com/Malinowsk/Arquitectura-Web/assets/70240593/afb18523-3c88-428c-8558-fc5a6e225a03)

Si enviamos esa solicitud, nos devolvera la lista de estudiantes, ordenadas por numero de libreta universitaria (universityNotebookNumber).

Si esta solicitud la ejecutamos desde internet, obtendremos como resultado el siguiente arreglo:

![image](https://github.com/Malinowsk/Arquitectura-Web/assets/70240593/b5801f61-cdbb-4c36-bdb6-c49a46d37275)

Con los siguientes datos:

![image](https://github.com/Malinowsk/Arquitectura-Web/assets/70240593/d1929e3b-d5e5-4224-9ec5-2046312a3aaf)

etc...

--------------------------------------------------------------------------------------------------------------------
### Recuperar un estudiante por numero de libreta universitaria

Para recuperar un estudiante específico en base a su número de libreta universitaria, utilizamos una solicitud GET a la URL ```http://localhost:8080/students/{universityNotebookNumber}```, donde `{universityNotebookNumber}` es el número de libreta universitaria del estudiante, el cual vendria a ser su ID.

En este caso traeremos el estudiante con universityNotebookNumber = 8, el que previamente fue añadido.

![image](https://github.com/Malinowsk/Arquitectura-Web/assets/70240593/04c43722-7c59-4626-81a2-6f36070f2d61)


Analizamos la respuesta para verificar que se haya recuperado el estudiante correcto.

![image](https://github.com/Malinowsk/Arquitectura-Web/assets/70240593/a1cef335-e56f-4623-a0fc-2aab08b99e3a)


--------------------------------------------------------------------------------------------------------------------
### Recuperar estudiantes por genero
Para recuperar estudiantes en base a su género, realizamos una solicitud GET a la URL ```http://localhost:8080/students/gender/{gender}```, donde `{gender}` es el género deseado.

```http://localhost:8080/students/gender/{gender}```

En este caso vamos a filtrar for genero 'f' (femenino)

![image](https://github.com/Malinowsk/Arquitectura-Web/assets/70240593/5735cff2-3d5b-4ac3-9586-f0b3abdbd77d)

Y obtendremos como resultado:

![image](https://github.com/Malinowsk/Arquitectura-Web/assets/70240593/8fc6c31f-524c-40ad-a608-671fb234b789)

Podemos observar que la respuesta obtenida de la Query, coincide con la base de datos

![image](https://github.com/Malinowsk/Arquitectura-Web/assets/70240593/34e6a0c0-1615-46e2-aea6-faf16e01864a)

--------------------------------------------------------------------------------------------------------------------
### Recuperar carreras con estudiantes inscriptos
* Para recuperar las carreras con estudiantes inscritos, efectuamos una solicitud GET a la URL `/careers/"COMPLETR ENDPOINT"`. Analizamos la respuesta para obtener una lista de carreras ordenadas por la cantidad de estudiantes inscritos en cada una.
--------------------------------------------------------------------------------------------------------------------
### Recuperar estudiantes de una carrera por ciudad de residencia
* Para recuperar los estudiantes de una carrera específica filtrados por ciudad de residencia, utilizamos una solicitud GET a la URL `/careers/"{careerId]/students/byCity/{city}`, donde `{careerId}` es el ID de la carrera y `{city}` es la ciudad deseada. Examinamos la respuesta para obtener la lista de estudiantes que cumplen con ambos criterios.
--------------------------------------------------------------------------------------------------------------------
### Generar un reporte de carreras
*Finalmente, para generar un reporte de carreras que incluya información sobre los inscritos y egresados por año, realizamos una solicitud GET a la URL `/careers/report`. Analizamos la respuesta para obtener el informe deseado, que está ordenado alfabéticamente y presenta los años de manera cronológica.
