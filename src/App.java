import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;



public class App {
    public static void main(String[] args) throws Exception {


        // Fazer uma conexão HTTP e buscar os top 250 filmes.
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request , BodyHandlers.ofString());
        String body = response.body();
        

        
        // Pegar só os dados que interessam (Titulo, poster, classificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>>listaDeFilmes = parser.parse(body);
        



        // Exibir e manipular os dados.

        for (int i = 0; i < 3; i++){
        Map<String,String> filme = listaDeFilmes.get(i);
            System.out.println("\u001B[1m Titulo: \u001B[m " + filme.get("title"));
            System.out.println("\u001b[1m Poster do Filme: \u001b[m " + filme.get("image"));
            System.out.println("\u001b[1m Clasificação: \u001b[m " + filme.get("imDbRating"));

            
            double classificacao = Double.parseDouble(filme.get("imDbRating"));
            int numeroDeEstrelinhas = (int)classificacao;
            


            for (int n = 1; n <= numeroDeEstrelinhas; n++) {
                System.out.print("\u001b[37;1m \u001b[44;1m ⭐\u001b[m");
            }
            System.out.println("\n");
        }


    }
}
