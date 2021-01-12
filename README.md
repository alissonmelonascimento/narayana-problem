# narayana-problem project

Projeto que evidencia o problema de devolução de conexões com o banco de dados ao POOL.

## Executando a aplicação

You can run your application in dev mode that enables live coding using:
```shell script
docker-compose up
```

```shell script
./mvnw compile quarkus:dev
```

## Testando a aplicação

Faça 20 chamadas a URL: http://localhost:8080/users. Obs: Pool de conexões padrão criado pelo quarkus com 20 conexões. Se quiser alterar, defina a propriedade **quarkus.datasource.jdbc.max-size**

Na 21 chamada, a aplicação dá erro e exibe o seguinte erro no LOG

**java.sql.SQLException: Sorry, acquisition timeout!**

## Análise do problema

Isso ocorre, pois todos os métodos envolvidos não possuem a anotação @Transactional().

Dessa forma, o gerenciador assume como padrão a anotação @Transactional(value = Transactional.TxType.SUPPORTS)

Se adicionar a anotação @Transactional(), funciona.
