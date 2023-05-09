# Consumir API Externa com Spring

## O Começo

Após começar a aprender Spring, uma das minhas maiores duvidas era como consumir uma API externa. Por mais que a criação de uma API seja extremamente necessária no Backend, o consumo dela também tem a mesma importância e portanto comecei a aprender mais sobre.

Nota: Qualquer correção será bem vinda

## Objetivo

Eu adoro futebol e portanto meu objetivo foi conseguir utilizar de alguma API externa sobre futebol, para isso eu acessei o seguinte repositório:

[https://github.com/public-apis/public-apis](https://github.com/public-apis/public-apis#sports--fitness)

Após análise de alguns repositórios resolvi utilizar a seguinte API

[https://www.api-football.com/documentation-v3#section/Introduction](https://www.api-football.com/documentation-v3#section/Introduction)

Esta API utiliza de um Key que só será disponibilizada após o cadastro no site deles, portanto, se deseja seguir o tutorial deve se cadastrar na API 

## Depedências

Para o projeto utilizei apenas a biblioteca Spring Web Flux com o Maven e o Lombok para configurações básicas:

```java
<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-webflux</artifactId>
</dependency>
<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
</dependency>
```

## Aprendizado

### Passo 1 - Configuração do WebClient

Comecei aprendendo sobre a interface Spring WebClient que é uma interface Java para executar requisições HTTP, por ela ser uma interface podemos inicializar ela de algumas formas, porém optei pelo seguinte método dentro de uma nova classe de configuração:

```java

@Configuration
public class FootballWebClientConfiguration {
	@Bean
	public WebClient webclient() {
		WebClient webClient = WebClient.builder()
				.baseUrl("https://v3.football.api-sports.io")
				.build();
		return webClient;
	}
}
```

O método acima retorna uma instância de WebClient e através da Anotation “@Bean” o Spring é capaz de gerenciar o objeto.

Para configuração básica, apenas coloquei o caminho base da API consumida e o método .build() criará um objeto WebClient com as configurações passadas.

### Passo 2 - Criando o modelo

Inicialmente vamos pegar os dados de um time da API com base no ID passado na url, para isso precisamos mapear os dados que serão retornados com o JSON.

Importante avisar que os nome tem que ser iguais aos da API, ou seja se o campo é “venue” o nome do atributo também tem que ser “venue”, portanto é necessário acompanhar a documentação da API

[https://www.api-football.com/documentation-v3#tag/Teams/operation/get-teams](https://www.api-football.com/documentation-v3#tag/Teams/operation/get-teams)

Felizmente existe um site para realizar essa modelagem para você, com apenas o JSON retornado da API ele consegue identificar cada atributo para colocar na classe Java que será usada de modelo

[https://www.jsonschema2pojo.org/](https://www.jsonschema2pojo.org/)

### Passo 3 - Configurando o Client

Feito a modelagem, criaremos um Service para a aplicação, onde ficará os dados que serão retornados da API

Primeiramente devemos ter uma instância do WebClient que será gerenciada pelo Spring com base nas configurações que criamos no começo, através da Anottation @Autowired

```java
@Autowired
	WebClient webClient;
```

Feito isso criaremos um método que retornará um Mono do tipo<TeamResponse> (Classe que modelei os dados) com base no id passado como parâmetro 

```java
public Mono<TeamResponse> findAnTeamById(String id){
		
		return webClient
				.get()
				.uri("/teams?id=" + id)
				.header("Nome-Da-Key", "Valor-Da-Sua-Key")
				.retrieve()
				.bodyToMono(TeamResponse.class)
	}
```

Explicarei por etapas cada parte do método

1 - Mono → Uma stream reativa para comportar apenas 1 elemento, como queremos buscar apenas um time com base no Id, este será o tipo de retorno.

2- get() → Método que define que o tipo da requisição será GET

3 - .uri("/teams?id=" + id) → Passamos o identificador do método que será retornado através da requisição, segundo a documentação da API o uri seria esse, lembrando que a URL base foi passada quando configuramos o WebClient então só precisaremos completar

4 - .header("X-RapidAPI-Key", "Sua KEY") → A Api utiliza Key, portanto devemos passar no header da requisição a API Key para conseguir acessar os dados 

5 - .retrieve() → Método que recupera os dados da requisição para ser convertida em algum tipo posteriormente

6 - .bodyToMono(TeamResponse.class) → Decodifica as informações para retornar um tipo Mono do método

Finalizando isso o Cliente estará pronto para ser utilizado

### Passo 4 - Fazendo as requisições

Por fim o WebCliente estará pronto para ser chamado pela classe controller

```java
@RestController
@AllArgsConstructor
@RequestMapping("/webclientfootball")
public class FootballAPIController {
	
	FootballClient footballClient;
	
	@GetMapping("/teams/{id}")
	public Mono<TeamResponse> getTeamById(@PathVariable String id){
		return footballClient.findAnTeamById(id);
	}
}
```

No Controller apenas configuramos a url que irá ser do tipo Get para retornar um time com base no id passado

Modelo da url: [http://localhost:8080/webclientfootball/teams/12](http://localhost:8080/webclientfootball/teams/12)

Retorno Desejado:

![RetornoEsperado](https://raw.githubusercontent.com/atilaacedo/Consumindo-API-Externa-Com-WebClient/main/Assets/Retorno-Esperado-Times.png))

Por fim, você pode consumir a API de diversas formas, porém utilizei o WebClient do Spring para mostrar uma boa alternativa e documentar meu aprendizado
