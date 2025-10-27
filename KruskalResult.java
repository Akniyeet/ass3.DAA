class KruskalResult {
    Edge[] mstEdges;
    int totalCost;
    int operationsCount;
    double executionTime;

    public KruskalResult(Edge[] mstEdges, int totalCost, int operationsCount, double executionTime) {
        this.mstEdges = mstEdges;
        this.totalCost = totalCost;
        this.operationsCount = operationsCount;
        this.executionTime = executionTime;
    }
}
