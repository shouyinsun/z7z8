package z7z8.leetcode.directedGraph;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 0,0,1,1
 * 1,0,2,2
 * 2,0,3,1
 * 3,2,1,3
 * 4,3,1,1
 * 5,2,3,1
 * 6,3,2,1
 */

public class DirectedGraph {

    private class Vertex{
        private String vertexLabel;// 顶点标识
        private List<Edge> adjEdges;
        private int inDegree;// 该顶点的入度

        public Vertex(String verTtexLabel) {
            this.vertexLabel = verTtexLabel;
            inDegree = 0;
            adjEdges = new LinkedList<Edge>();
        }
    }

    private class Edge {
        private Vertex endVertex;
        // private double weight;
        public Edge(Vertex endVertex) {
            this.endVertex = endVertex;
        }
    }

    private Map<String, Vertex> directedGraph;

    public DirectedGraph(String graphContent) {
        directedGraph = new LinkedHashMap<String, DirectedGraph.Vertex>();
        buildGraph(graphContent);
    }

    private void buildGraph(String graphContent) {
        String[] lines = graphContent.split("\n");
        Vertex startNode, endNode;
        String startNodeLabel, endNodeLabel;
        Edge e;
        for (int i = 0; i < lines.length; i++) {
            String[] nodesInfo = lines[i].split(",");
            startNodeLabel = nodesInfo[1];
            endNodeLabel = nodesInfo[2];
            startNode = directedGraph.get(startNodeLabel);
            if(startNode == null){
                startNode = new Vertex(startNodeLabel);
                directedGraph.put(startNodeLabel, startNode);
            }
            endNode = directedGraph.get(endNodeLabel);
            if(endNode == null){
                endNode = new Vertex(endNodeLabel);
                directedGraph.put(endNodeLabel, endNode);
            }

            e = new Edge(endNode);//每读入一行代表一条边
            startNode.adjEdges.add(e);//每读入一行数据,起始顶点添加一条边
            endNode.inDegree++;//每读入一行数据,终止顶点入度加1
        }
    }

    public void topSort() throws Exception{
        int count = 0;

        Queue<Vertex> queue = new LinkedList<>();// 拓扑排序中用到的栈,也可用队列.
        //扫描所有的顶点,将入度为0的顶点入队列
        Collection<Vertex> vertexs = directedGraph.values();
        for (Vertex vertex : vertexs)
            if(vertex.inDegree == 0)
                queue.offer(vertex);

        while(!queue.isEmpty()){
            Vertex v = queue.poll();
            System.out.print(v.vertexLabel + " ");
            count++;
            for (Edge e : v.adjEdges)
                if(--e.endVertex.inDegree == 0)
                    queue.offer(e.endVertex);
        }
        if(count != directedGraph.size())
            throw new Exception("Graph has circle");
    }
}