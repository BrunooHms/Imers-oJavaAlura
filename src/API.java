public enum API {

    GITHUB_MOST_POPULAR_TVS("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json", new ExtratorDeConteudoDoGitHub()),
    NASA("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14", new ExtratorDeConteudoDaNasa());

    private String url;
    private ExtratorDeConteudo extrator;

    API(String url, ExtratorDeConteudo extrator){


        this.url = url;
        this.extrator = extrator;
    }

    public ExtratorDeConteudo getExtrator() {
        return extrator;
    }

    public String getUrl() {
        return url;
    }
    
}
