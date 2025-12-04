import java.util.Scanner;

public class GraphDriver
{

    /**
     * Displays a menu of options and handles user input to perform graph operations
     * @param input Scanner for reading user input
     * @param siteAmount Number of sites in the graph
     * @param SitesArray Array containing all site objects
     * @param edges 2D array representing connections between sites with weights
     */
    public static void Menu(Scanner input, int siteAmount, Sites[] SitesArray, int[][] edges)
    {
        int menuChoice = 0;
        String site;
        String site1;
        String site2;
        int weight;

        System.out.println("Menu:");
        System.out.println("1. Search for a site");
        System.out.println("2. Insert a connection between two sites");
        System.out.println("3. Display all connections for a site");
        System.out.println("4. Find the closest site to a given site");
        System.out.println("5. Exit");

        menuChoice = input.nextInt();
        input.nextLine(); // Consume newline
        
        switch(menuChoice)
        {   
        case 1:
            System.out.println("Enter site name to search:");
            site = input.nextLine();
            System.out.println(Search(site, siteAmount, SitesArray));
            break;
        case 2:
            System.out.println("Enter site1 name:");
            site1 = input.nextLine();
            System.out.println("Enter site2 name:");
            site2 = input.nextLine();
            System.out.println("Enter weight:");
            weight = input.nextInt();
            Insert(site1, site2, weight, siteAmount, SitesArray, edges);
            break;
        case 3:
            System.out.println("Enter site name to display all connections:");
            site = input.nextLine();
            Allcons(site, siteAmount, SitesArray, edges);
            break;
        case 4:
            System.out.println("Enter site name to find the closest site:");
            site = input.nextLine();
            Closest(site, siteAmount, SitesArray, edges);
            break;
        case 5:
            System.out.println("Exiting the program.");
            break;
        default:
            System.out.println("Invalid choice. Please try again.");
            break;
        }
    }


    /**
     * Searches for a specific site by name and returns its details
     * @param site Name of the site to search for
     * @param siteAmount Total number of sites in the array
     * @param SitesArray Array containing all site objects
     * @return String containing site details or "Site not found"
     */
    public static String Search(String site, int siteAmount, Sites[] SitesArray)
    {
        for(int i = 0; i < siteAmount; i++)
        {
            // Case-insensitive comparison to find the site
            if(site.equalsIgnoreCase(SitesArray[i].getName()))
            {
                return SitesArray[i].toString();
            }
        }
        return "Site not found";
    }

    /**
     * Creates a connection between two sites with a specified weight
     * @param site1 Name of the first site
     * @param site2 Name of the second site  
     * @param weight The weight/distance of the connection
     * @param siteAmount Total number of sites
     * @param SitesArray Array containing all site objects
     * @param edges 2D array to store the connection weights
     */
    public static void Insert(String site1, String site2, int weight, int siteAmount, Sites[] SitesArray, int[][] edges)
    {
        int site1Index = -1;
        int site2Index = -1;
        
        // Find the indices of both sites in the array
        for (int i = 0; i < siteAmount; i++) 
        {
            
            if(SitesArray[i].getName().equalsIgnoreCase(site1))
            {
                site1Index = i;
            }
            else if(SitesArray[i].getName().equalsIgnoreCase(site2))
            {
                site2Index = i;
            } 
        }
        
        // Check if sites are different and both exist
        if(site1Index != site2Index)
        {
            if(site1Index != -1 && site2Index != -1)
            {
                // Create the connection with the specified weight
                edges[site1Index][site2Index] = weight;
            }
            else
            {
                System.out.println("Sites not found ");
            }
        }
        else   
        {
            System.out.println("Cant create a connection to same site");
        }
    }

    /**
     * Displays all connections for a specific site
     * @param site Name of the site to show connections for
     * @param siteAmount Total number of sites
     * @param SitesArray Array containing all site objects
     * @param edges 2D array containing connection weights
     */
    public static void Allcons(String site, int siteAmount, Sites[] SitesArray, int[][] edges)
    {
        int rowIndex = 0;

        for(int i = 0; i < siteAmount; i++)
        {
            if(site.equals(SitesArray[i].getName()))
            {
                rowIndex = i;
                break;
            }
        }

        // Check all possible connections from this site
        for(int col = 0; col < siteAmount; col++)
        {
            if(col != rowIndex && edges[rowIndex][col] != 0)
            {
                System.out.println(SitesArray[col].toString());
            }
        }
    }

    /**
     * Finds and displays the closest connected site to a given site
     * @param site Name of the site to find closest neighbor for
     * @param siteAmount Total number of sites
     * @param SitesArray Array containing all site objects
     * @param edges 2D array containing connection weights
     */
    public static void Closest(String site, int siteAmount, Sites[] SitesArray, int[][] edges)
    {
        int rowIndex = 0;
        int minDist = Integer.MAX_VALUE;
        int closestIndex = -1;

        for(int i = 0; i < siteAmount; i++)
        {
            if(site.equals(SitesArray[i].getName()))
            {
                rowIndex = i;
                break;
            }
        }

        // Find the connection with minimum weight
        for(int col = 0; col < siteAmount; col++)
        {
            if(col != rowIndex && edges[rowIndex][col] != 0)
            {
                 if(col != rowIndex && edges[rowIndex][col] != 0 && edges[rowIndex][col] < minDist)
                {
                    minDist = edges[rowIndex][col];
                    closestIndex = col;
                }
            }
        }
        System.out.println(SitesArray[closestIndex].toString());

    }

    public static void main(String[] args)
    {
        final int siteAmount = 10;
  
        // Initialize data structures
        Sites[] sitesArray = new Sites[10];
        int[][] edges = new int[siteAmount][siteAmount]; // Adjacency matrix for graph
        Scanner input = new Scanner(System.in);

        // Initialize all edges to 0 (no connection)
        for(int row = 0; row < siteAmount; row++)
        {
            for(int col = 0; col < siteAmount; col++)
            {
                edges[row][col] = 0;
            }
        }

        // Set up predefined connections between sites
        edges[0][1] = 3;
        edges[1][0] = 3;
        edges[1][2] = 2;
        edges[1][3] = 2;
        edges[2][1] = 2;
        edges[2][3] = 4;
        edges[3][1] = 2;
        edges[3][2] = 4;
        edges[3][4] = 1;
        edges[3][5] = 1;
        edges[4][3] = 1;
        edges[4][5] = 2;
        edges[4][9] = 9;
        edges[5][3] = 1;
        edges[5][4] = 2;
        edges[5][6] = 2;
        edges[5][8] = 2; 
        edges[6][5] = 2;
        edges[6][7] = 3;
        edges[7][6] = 3;
        edges[7][8] = 1;
        edges[8][5] = 1;
        edges[8][7] = 1;
        edges[8][9] = 5;
        edges[9][4] = 9;
        edges[9][8] = 5;
        
        // Create site objects with names and coordinates
        sitesArray[0] = new Sites("Whitehouse", 167, 98);
        sitesArray[1] = new Sites("Tracys", 201, 134);
        sitesArray[2] = new Sites("D-Bar", 89, 123);
        sitesArray[3] = new Sites("Holohans", 156, 78);
        sitesArray[4] = new Sites("The Baileys", 234, 167);
        sitesArray[5] = new Sites("The Antique", 189, 45);
        sitesArray[6] = new Sites("Stamps", 67, 234);
        sitesArray[7] = new Sites("Rockhards", 45, 23);
        sitesArray[8] = new Sites("Dawsons", 78, 156);
        sitesArray[9] = new Sites("Donohoes", 123, 89);

        // Start the interactive menu
        Menu(input, siteAmount, sitesArray, edges);

    }
}