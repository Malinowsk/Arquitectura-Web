[![Postman](https://img.shields.io/badge/Postman-Testing-brightgreen.svg)](https://www.postman.com/)
# Trabajo Entregable 3

 ## 3) Testear la invocación a los servicios REST mediante Postman, o cliente similar.
   - **2a-** [Dar de alta un Estudiante](#dar-de-alta-un-estudiante)
   - **2b-** [Matricular un estudiante en una carrera](#matricular-un-estudiante-en-una-carrera)
   - **2c-** [Recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple](#recuperar-todos-los-estudiantes-ordenados-por-apellido)
   - **2d-** [Recuperar un estudiante, en base a su número de libreta universitaria.](#recuperar-un-estudiante-por-numero-de-libreta-universitaria)
   - **2e-** [Recuperar estudiantes en base a su genero](#recuperar-estudiantes-por-genero)
   - **2f-** [Recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos](#recuperar-carreras-con-estudiantes-inscriptos)
   - **2g-** [Recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.](#recuperar-estudiantes-de-una-carrera-por-ciudad-de-residencia)
   - **2h-** [Generar un reporte de las carreras, que para cada carrera incluya información de los inscriptos y egresados por año. Se deben ordenar las carreras alfabéticamente, y presentar los años de manera cronológica.](#generar-un-reporte-de-carreras)
  
  A continuacion mostraremos la resolucion del punto 3, teniendo en cuenta los incisos de la pregunta 2 utilizando Postman para los servicios REST.
  
--------------------------------------------------------------------------------------------------------------------
### Dar de alta un estudiante
Para dar de alta un estudiante en el sistema, realizamos una solicitud POST a la URL ```  http://localhost:8080/students ```

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

![image](https://github.com/Malinowsk/Arquitectura-Web/assets/70240593/11d8ca0d-e23e-4792-b08a-2dc6789f9491)

Con la insercion del estudiante en la tabla students:

![image](https://github.com/Malinowsk/Arquitectura-Web/assets/70240593/a3b7a7e6-435f-4a3c-ba23-c2d851666716)

--------------------------------------------------------------------------------------------------------------------
### Matricular un estudiante en una carrera
Para matricular un estudiante en una carrera, utilizamos una solicitud POST a la URL ```http://localhost:8080/inscriptions```

En el cuerpo de la solicitud, especificamos el ID de la carrera en la que deseamos matricularlo, el numero de libreta universitaria (id del alumno) y la fecha de ingreso. 
```
{   
  "career_id":2,
  "student_notebook_number":8,
  "fecha_ingreso": "2023-10-06T15:18:45.011+00:00"
}
```  
Verificamos la respuesta para asegurarnos de que la matriculación se haya realizado con éxito, y obtendremos como resultado:

![image](https://github.com/Malinowsk/Arquitectura-Web/assets/70240593/6d29ff68-5aa2-4bf9-8328-92213cec9af9)

Con la insercion del estudiante en la tabla students:

![image](https://github.com/Malinowsk/Arquitectura-Web/assets/70240593/71817eaf-9202-4b06-b854-ab89c9835db8)

--------------------------------------------------------------------------------------------------------------------
### Recuperar todos los estudiantes ordenados por apellido
Para recuperar todos los estudiantes registrados en el sistema, realizamos una solicitud GET a la URL ```http://localhost:8080/students/sortBySurname```

 ![image](https://github.com/Malinowsk/Arquitectura-Web/assets/70240593/5ed06b44-3d3a-4955-885f-54a3c1ffb0ac)

Si enviamos esa solicitud, nos devolvera la lista de estudiantes, ordenadas por su apellido:

![image](https://github.com/Malinowsk/Arquitectura-Web/assets/70240593/97c06147-8ac4-4e8a-903a-3844cfd29af1)

--------------------------------------------------------------------------------------------------------------------
### Recuperar un estudiante por numero de libreta universitaria

Para recuperar un estudiante específico en base a su número de libreta universitaria, utilizamos una solicitud GET a la URL ```http://localhost:8080/students/{universityNotebookNumber}```, donde `{universityNotebookNumber}` es el número de libreta universitaria del estudiante, el cual vendria a ser su ID.

En este caso traeremos el estudiante con universityNotebookNumber = 8, el que previamente fue añadido.

GET ```http://localhost:8080/students/8```

![image](https://github.com/Malinowsk/Arquitectura-Web/assets/70240593/04c43722-7c59-4626-81a2-6f36070f2d61)


Analizamos la respuesta para verificar que se haya recuperado el estudiante correcto.

![image](https://github.com/Malinowsk/Arquitectura-Web/assets/70240593/5a2b7df1-406d-4fec-9efa-3616c6598abf)

--------------------------------------------------------------------------------------------------------------------
### Recuperar estudiantes por genero
Para recuperar estudiantes en base a su género, realizamos una solicitud GET a la URL ```http://localhost:8080/students/gender/{gender}``` donde `{gender}` es el género deseado.

En este caso vamos a filtrar for genero 'f' (femenino)

GET ```http://localhost:8080/students/gender/f```

![image](https://github.com/Malinowsk/Arquitectura-Web/assets/70240593/5735cff2-3d5b-4ac3-9586-f0b3abdbd77d)

Y obtendremos como resultado:

![image](https://github.com/Malinowsk/Arquitectura-Web/assets/70240593/e3ce8f19-cefb-4e5e-a52d-81333950eec4)

Podemos observar que la respuesta obtenida de la Query, coincide con la base de datos

![image](https://github.com/Malinowsk/Arquitectura-Web/assets/70240593/8028321f-9d63-41ca-b26b-6ae840d44c99)

--------------------------------------------------------------------------------------------------------------------
### Recuperar carreras con estudiantes inscriptos

Para recuperar las carreras con estudiantes inscriptos, efectuamos una solicitud GET a la URL ```http://localhost:8080/careers/sortByCantInsc```

![image](https://github.com/Malinowsk/Arquitectura-Web/assets/70240593/d329c418-95f6-4dd9-9cd2-1047830de0f1)

Obtendremos como resultado:

![image](https://github.com/Malinowsk/Arquitectura-Web/assets/70240593/3461909b-8d36-43bf-a064-957ce840fca3)

--------------------------------------------------------------------------------------------------------------------
### Recuperar estudiantes de una carrera por ciudad de residencia
Para recuperar los estudiantes de una carrera específica filtrados por ciudad de residencia, utilizamos una solicitud GET a la URL ```http://localhost:8080/students/findByCareerAndCity/{careerId}/{city}```, donde `{careerId}` es el ID de la carrera y `{city}` es la ciudad deseada.

```http://localhost:8080/students/findByCareerAndCity/2/tandil```

![image](https://github.com/Malinowsk/Arquitectura-Web/assets/70240593/34e8b902-34d5-4620-9c64-9124753942ea)

En este caso, vamos a obtener una lista de estudiantes, que estudien la carrera Tudai(careerId=2) y que sean de la ciudad de Tandil (city).

Examinamos la respuesta para obtener la lista de estudiantes que cumplen con ambos criterios:

![image](https://github.com/Malinowsk/Arquitectura-Web/assets/70240593/f2218acc-b349-440d-9d01-96ba2c7e7e21)

Podemos observar que el JSON nos devuelve al estudiante que ingresamos en el punto 2a, y luego inscribimos en el 2b.

--------------------------------------------------------------------------------------------------------------------
### Generar un reporte de carreras
Finalmente, para generar un reporte de carreras que incluya información sobre los inscritos y egresados por año, realizamos una solicitud GET a la URL `http://localhost:8080/inscriptions/report`. Analizamos la respuesta para obtener el informe deseado, que está ordenado alfabéticamente y presenta los años de manera cronológica.

GET ```http://localhost:8080/inscriptions/report```

![image](https://github.com/Malinowsk/Arquitectura-Web/assets/70240593/e82e5905-2e3f-4b80-b9f0-2467bfa81fb4)

Al ejecuitarlo, obtendremos como resultado:

![image](https://github.com/Malinowsk/Arquitectura-Web/assets/70240593/06b83392-8ebb-4377-81fc-3cb640730b2e)

El JSON nos devolvera los resultados, ordenados por carrera y año, junto con la cantidad de inscriptos y egresados que tiene ese año.

--------------------------------------------------------------------------------------------------------------------

