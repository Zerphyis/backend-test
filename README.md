# Parking Manager – Sistema de Gestão de Estacionamento
### Teste Técnico Backend – GCP Global

Este projeto é uma solução completa de gestão de estacionamento, desenvolvida com foco em clareza, robustez, baixa complexidade e alta extensibilidade, seguindo princípios de Arquitetura Limpa e o Padrão Strategy para tarifação.

### Objetivo do Sistema

O sistema é capaz de:

#### Registrar entrada e saída de veículos.

#### Aplicar regras de cobrança específicas por categoria:

Oficial

Residente

Não-Residente

#### Controlar e persistir estadias.

#### Gerar relatórios mensais para veículos residentes.

#### Permitir expansão futura seguindo o Princípio Aberto/Fechado (OCP).

### 🛠️ Tecnologias Utilizadas
````
Linguagem	Java 17+
Framework	Spring Boot 3
Persistência	Spring Data JPA, Hibernate, MySQL
Build	Maven
Web	Spring Web
MySQL como banco de dados principal.
````


### Arquitetura e Princípios

O projeto segue uma arquitetura em camadas inspirada no Domain-Driven Design (DDD), garantindo separação clara entre domínio e infraestrutura.

#### Estrutura de Pastas
````
/Application
   /Service
   /UseCases
/Domain
   /Entity
   /Enums
   /Factory
/Infra
   /Controller
   /Repositories
````

### Princípios Aplicados
#### Princípio	Descrição
#### OCP (Open/Closed Principle)	Novos tipos de veículo ou novas tarifas podem ser adicionadas sem modificar o serviço central.
#### Strategy Pattern	Cada categoria de veículo possui sua própria regra de tarifação.
#### SRP (Single Responsibility)	Cada classe possui apenas uma responsabilidade.
### 🧩 Entidades Principais
#### VehicleDomain

Representa os veículos registrados.
````
Atributos:

id
plate
type (OFICIAL, RESIDENTE, NAO_RESIDENTE)
````
#### VehicleEntity

Registro da persistencia do veículo no estacionamento.

Atributos:
````
id
plate
typeVehicle
entryTime
exitTime
acumulatedTime
peding
````

### 🧠 Strategy Pattern para Tarifação

A tarifação é implementada através da interface ITarifador.

#### Tipo de Veículo	Classe	Regra
<br>
OFICIAL	Sempre paga 0
<br>
RESIDENTS	Acumula horas para cobrança mensal
<br>
NORESIDENTS		Calcula valor total no momento da saída


### EndPoints Completa da API

Base URL: /parking

### 1. Registrar Entrada
#### POST /parking/entry
<img width="894" height="543" alt="Image" src="https://github.com/user-attachments/assets/0beca504-8bac-47cc-b292-3f1c56c18308" />

#### Status:
<br>
✔ 200 OK
<br>
❌ 400 BAD REQUEST

###  2. Registrar Saída
#### POST /parking/exit
<img width="895" height="517" alt="Image" src="https://github.com/user-attachments/assets/98aea6fb-6811-4018-95b3-2ac9f89a8197" />

#### Status:
<br>
✔ 200 OK
<br>
❌ 404 NOT FOUND
<br>
❌ 400 BAD REQUEST

### 3. Cadastrar Residente
#### POST /parking/resident
<img width="886" height="541" alt="Image" src="https://github.com/user-attachments/assets/8f38ae7a-7ce6-4d7d-a08f-55d525edc482" />

#### Status:
<br>
✔ 200 OK
<br>
❌ 404 NOT FOUND

### 4. Cadastrar Veículo Oficial
#### POST /parking/official
<img width="894" height="538" alt="Image" src="https://github.com/user-attachments/assets/b0894bbc-d2a3-41c7-a9be-9abf937078ed" />

#### Status:
<br>
✔ 200 OK
<br>
❌ 404 NOT FOUND

### 5. Listar Todos os Veículos
#### GET /parking/vehicles

<img width="944" height="676" alt="Image" src="https://github.com/user-attachments/assets/22f45035-a353-4b62-95c8-641d5aef9644" />

### Status
<br>
✔ 200 OK

### 6. Buscar Veículo pela Placa
#### GET /parking/vehicles/{plate}

<img width="878" height="563" alt="Image" src="https://github.com/user-attachments/assets/2137a8f6-e6b9-492a-a2bf-02427061cf84" />
<br>

#### Status
<br>
✔ 200 OK
<br>
❌ 404 NOT FOUND

###  7. Listar por Tipo
### GET /parking/vehicles/type/{type}
<img width="907" height="550" alt="Image" src="https://github.com/user-attachments/assets/0c7fc268-d547-49c0-b5a5-722b2d82b917" />

### Tipos válidos:
<br>
OFICIAL
<br>
RESIDENTS
<br>
NORESIDENTS

### Status 
<br>
✔ 200 OK
<br>
❌ 404 NOT FOUND

### 8. Iniciar um Novo Mês
#### POST /parking/start-month
<img width="921" height="460" alt="Image" src="https://github.com/user-attachments/assets/7c8df842-b3cb-4799-ac73-46121c5a8fc9" />

### Status:

✔  204 NO CONTENT

####  9. Gerar Relatório
### GET /parking/report
<img width="970" height="431" alt="Image" src="https://github.com/user-attachments/assets/744052a6-73f0-443d-bab1-54acc1da2511" />

### Status:

<br>
✔ 200 OK
<br>
❌ 404 NOT FOUND


### Execução do Projeto
#### Pré-requisitos
````
Java 17+
Maven
Git
MySQL (configurado para a aplicação)
````

#### Passos para Executar
Clonar o repositório:
````
git clone https://github.com/Zerphyis/backend-test.git
cd backend-test
````

### Configuração do MySQL:

Certifique-se de que as configurações de conexão (URL, usuário e senha) no application.properties ou application.yml estejam corretas para o seu ambiente MySQL.

### Executar a aplicação:
````
./mvnw spring-boot:run
````
