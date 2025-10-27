class Result {
    int graph_id;
    InputStats input_stats;
    KruskalResult kruskal;

    public Result(int graph_id, InputStats input_stats, KruskalResult kruskal) {
        this.graph_id = graph_id;
        this.input_stats = input_stats;
        this.kruskal = kruskal;
    }
}
