## Actividad 3. Historial de acciones

**Action History Application

**Descripción:

Aplicación Android desarrollada en Kotlin que implementa persistencia de datos local
utilizando Room (SQLite). La app permite gestionar categorías y notas con una relación
1:N, realizar operaciones CRUD y registrar un historial de acciones (auditoría interna)
de las operaciones realizadas por el usuario.

El proyecto cumple con una arquitectura MVC (Model–View–Controller) y sigue
convenciones de nomenclatura estandarizadas.

**Instalación y ejecución

1. Clonar el repositorio:
   git clone https://github.com/christoferloal/Actividad-3.-Historial-de-acciones.git

2. Abrir el proyecto en Android Studio.
3. Sincronizar Gradle.
4. Ejecutar la aplicación en un emulador o dispositivo físico (API 24+).

**Estructura de la base de datos

Tabla: categories

| Campo         | Tipo         |
| ------------- | ------------ |
| category_id   | INTEGER (PK) |
| category_name | TEXT         |

Tabla: categories

| Campo         | Tipo         |
| ------------- | ------------ |
| category_id   | INTEGER (PK) |
| category_name | TEXT         |

Tabla: notes

| Campo      | Tipo         |
| ---------- | ------------ |
| history_id | INTEGER (PK) |
| action     | TEXT         |
| created_at | INTEGER      |
| details    | TEXT         |

**Capturas de pantalla de las interfaces de usuario.

### Pantalla de de inicio

<img width="1080" height="2340" alt="Screenshot_20251214_230723" src="https://github.com/user-attachments/assets/27cd2a49-92ed-4c43-a9cd-162192a08f6b" />

<img width="1080" height="2340" alt="Screenshot_20251214_230732" src="https://github.com/user-attachments/assets/86fd5a2f-d2da-4293-87ec-0dde0612d54f" />

<img width="1080" height="2340" alt="Screenshot_20251214_230742" src="https://github.com/user-attachments/assets/a91b8855-d640-4532-a061-ed3a600279b7" />

<img width="1080" height="2340" alt="Screenshot_20251214_230752" src="https://github.com/user-attachments/assets/f92ae63e-3234-4543-8b38-d5f1b2660f85" />

<img width="1080" height="2340" alt="Screenshot_20251214_230800" src="https://github.com/user-attachments/assets/0235d3cd-06ab-4bd6-9f75-6a6c4e4c2451" />

