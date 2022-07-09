
## NoteBook Management

- GET /api/notebooks (get a list of notebook)
- POST /api/notebooks (create a notebook)
- GET /api/notebooks/1 (get one notebook from the list)
- PATCH /api/notebooks/1 (update the price of a single notebook)
- DELETE /api/notebooks/1 (delete a single notebook)

## How to setup
Please don't forget install Docker! Running below command in project's directory builds 2 running containers, NotebookService and Mysql containers with initial records

```bash
docker-compose up
```

## How to use
This app is Restful and follows OpenAPI specification. You can view the endpoint documentation, try its functions, and see the response in a GUI. After running the containers, you can access the application links in below lines:

- Application Rest services : http://localhost:8090/api/notebooks
- SwaggerUI : http://localhost:8090/swagger-ui.html



## Test

Tests are written with H2 in-memory database with initial records.
