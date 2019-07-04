# Arquitetura baseada em fila de mensagens

## Instruções para executar o projeto
Na raiz do projeto:
Execute os serviços da infraestrutura
```bash
./stack infra up
```
Execute os microsserviços
```bash
./stack services up
```

Para fazer uma requisição
```bash
cd orders-api
curl -X POST -H 'Content-Type: application/json' -d @body.json http://localhost:8080/orders
```
