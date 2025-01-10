import java.util.*;

public class Graph {
    private final Set<Node> nodes;
    private final Set<Edge> edges;
    private final Map<String, String> attributes;

    public Graph() {
        this.nodes = new HashSet<>();
        this.edges = new HashSet<>();
        this.attributes = new HashMap<>();
    }

    public Graph(Map<String, String> attributes) {
        this();
        this.attributes.putAll(attributes);
    }

    public Collection<Node> getNodes() {
        return Collections.unmodifiableSet(nodes);
    }

    public Collection<Edge> getEdges() {
        return Collections.unmodifiableSet(edges);
    }

    public Graph node(String name) {
        nodes.add(new Node(name));
        return this;
    }

    public Graph node(String name, Map<String, String> attributes) {
        nodes.add(new Node(name, attributes));
        return this;
    }

    public Graph edge(String start, String end) {
        edges.add(new Edge(start, end));
        return this;
    }

    public Graph edge(String start, String end, Map<String, String> attributes) {
        edges.add(new Edge(start, end, attributes));
        return this;
    }

    public Map<String, String> getAttributes() {
        return Collections.unmodifiableMap(attributes);
    }
}