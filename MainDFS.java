
import java.util.Scanner;
// Imports Scanner to receive input from the user.

abstract class AbstractClassDFS {
    // Creates the abstract parent class.

    abstract void dfs(int vertex, boolean[] visited);
    // Declares the abstract DFS method.
    // The subclass must provide its implementation.
}

class SubclassDFS extends AbstractClassDFS {
    // Creates a subclass that inherits from AbstractClassDFS.

    int[][] matrix;
    // Stores graph connections using a 2D array.

    int vertices;
    // Stores the total number of vertices.

    public SubclassDFS(int vertices) {
        // Constructor that receives the number of vertices.

        this.vertices = vertices;
        // Saves the number of vertices.

        matrix = new int[vertices][vertices];
        // Creates a square adjacency matrix.
        // All elements initially contain 0.
    }

    void addEdge(int source, int destination) {
        // Adds a connection between two vertices.

        matrix[source][destination] = 1;
        // Marks the connection from source to destination.

        matrix[destination][source] = 1;
        // Marks the reverse connection because the graph
        // is undirected.
    }

    void displayMatrix() {
        // Displays the adjacency matrix.

        System.out.println("\nAdjacency Matrix:");
        // Prints the heading.

        for (int row = 0; row < vertices; row++) {
            // Goes through every row.

            for (int column = 0; column < vertices; column++) {
                // Goes through every column in the current row.

                System.out.print(matrix[row][column] + " ");
                // Prints the current matrix element.
            }

            System.out.println();
            // Moves to the next line after each row.
        }
    }

    @Override
    void dfs(int vertex, boolean[] visited) {
        // Implements Depth-First Search recursively.

        visited[vertex] = true;
        // Marks the current vertex as visited.

        System.out.print(vertex + " ");
        // Prints the current vertex.

        for (int i = 0; i < vertices; i++) {
            // Checks every possible neighbor of this vertex.

            if (matrix[vertex][i] == 1 && !visited[i]) {
                // Checks if a connection exists and the neighbor
                // has not been visited yet.

                dfs(i, visited);
                // Recursively visits that neighbor before
                // continuing to the next neighbor.
            }
        }
    }
}

public class MainDFS {
    // Main class. Its name matches MainDFS.java.

    public static void main(String[] args) {
        // The program starts here.

        Scanner input = new Scanner(System.in);
        // Creates a Scanner for user input.

        System.out.print("Enter number of vertices: ");
        // Asks the user for the number of vertices.

        int vertices = input.nextInt();
        // Reads the number of vertices.

        if (vertices <= 0) {
            // Checks whether the number is invalid.

            System.out.println("Number of vertices must be positive.");
            // Displays an error message.

            input.close();
            // Closes the Scanner.

            return;
            // Stops the program.
        }

        SubclassDFS graph = new SubclassDFS(vertices);
        // Creates the graph object.

        System.out.print("Enter number of edges: ");
        // Asks how many connections the graph will have.

        int edges = input.nextInt();
        // Reads the number of edges.

        if (edges < 0) {
            // Checks whether the edge count is negative.

            System.out.println("Number of edges cannot be negative.");
            // Displays an error message.

            input.close();
            // Closes the Scanner.

            return;
            // Stops the program.
        }

        System.out.println("\nEnter connections:");
        // Displays the connection input heading.

        for (int i = 1; i <= edges; i++) {
            // Repeats for each edge.

            System.out.print("Edge " + i + ": ");
            // Asks for the source and destination on one line.

            int source = input.nextInt();
            // Reads the source vertex.

            int destination = input.nextInt();
            // Reads the destination vertex.

            if (source < 0 || source >= vertices ||
                destination < 0 || destination >= vertices) {
                // Checks whether either vertex is out of range.

                System.out.println("Invalid vertices. Try again.");
                // Displays an error message.

                i--;
                // Repeats this edge number.
            }
            else {
                // Runs if both vertices are valid.

                graph.addEdge(source, destination);
                // Adds the connection to the matrix.
            }
        }

        graph.displayMatrix();
        // Displays the completed adjacency matrix.

        System.out.print("\nEnter starting vertex: ");
        // Asks which vertex DFS should start from.

        int start = input.nextInt();
        // Reads the starting vertex.

        if (start < 0 || start >= vertices) {
            // Checks whether the starting vertex is invalid.

            System.out.println("Invalid starting vertex.");
            // Displays an error message.
        }
        else {
            // Runs when the starting vertex is valid.

            boolean[] visited = new boolean[vertices];
            // Creates an array to track visited vertices.
            // All elements initially are false.

            System.out.println("\nDFS Traversal:");
            // Displays the traversal heading.

            graph.dfs(start, visited);
            // Starts recursive DFS at the selected vertex.

            System.out.println();
            // Moves to the next line after traversal.
        }

        input.close();
        // Closes the Scanner.
    }
}
