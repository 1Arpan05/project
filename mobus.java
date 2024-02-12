import java.util.*;
public class mobus{
	static class Edge{
		int src;
		int dest;
		int wt;
		
		public Edge(int s,int d,int w) {
			this.src=s;
			this.dest=d;
			this.wt=w;
		}
	}
	
	public static void creategraph(ArrayList<Edge> graph[]) {
		for(int i=0;i<graph.length;i++) {
			graph[i]=new ArrayList<Edge>();
		}
		graph[0].add(new Edge(0,1,5));
		graph[0].add(new Edge(0,9,5));
		graph[0].add(new Edge(0,14,5));
		
		graph[1].add(new Edge(1,0,5));
		graph[1].add(new Edge(1,2,5));
		
		graph[2].add(new Edge(2,1,5));
		graph[2].add(new Edge(2,3,5));
		graph[2].add(new Edge(2,22,5));
		
		graph[3].add(new Edge(3,2,5));
		graph[3].add(new Edge(3,8,5));
		graph[3].add(new Edge(3,19,5));
		graph[3].add(new Edge(3,18,5));
		graph[3].add(new Edge(3,20,5));
		graph[3].add(new Edge(3,22,5));
		
		graph[4].add(new Edge(4,5,5));
		graph[4].add(new Edge(4,14,5));
		graph[4].add(new Edge(4,13,5));
		
		graph[5].add(new Edge(5,4,5));
		graph[5].add(new Edge(5,14,5));
		graph[5].add(new Edge(5,13,5));
		
		graph[6].add(new Edge(6,9,5));
		graph[6].add(new Edge(6,16,5));
		graph[6].add(new Edge(6,7,5));
		
		graph[7].add(new Edge(7,17,5));
		graph[7].add(new Edge(7,6,5));
		graph[7].add(new Edge(7,16,5));
		graph[7].add(new Edge(7,18,5));
		graph[7].add(new Edge(7,19,5));
		graph[7].add(new Edge(7,8,5));
		graph[7].add(new Edge(7,9,5));
		
		graph[8].add(new Edge(8,7,5));
		graph[8].add(new Edge(8,18,5));
		graph[8].add(new Edge(8,19,5));
		graph[8].add(new Edge(8,3,5));
		graph[8].add(new Edge(8,20,5));

		graph[9].add(new Edge(9,0,5));
		graph[9].add(new Edge(9,6,5));
		graph[9].add(new Edge(9,7,5));
		
		graph[10].add(new Edge(10,11,5));
		
		graph[11].add(new Edge(11,10,5));
		graph[11].add(new Edge(11,12,5));
		
		graph[12].add(new Edge(12,11,5));
		graph[12].add(new Edge(12,13,5));
		
		graph[13].add(new Edge(13,12,5));
		graph[13].add(new Edge(13,4,5));
		graph[13].add(new Edge(13,5,5));
		graph[13].add(new Edge(13,14,5));
		
		graph[14].add(new Edge(14,15,5));
		graph[14].add(new Edge(14,0,5));
		graph[14].add(new Edge(14,4,5));
		graph[14].add(new Edge(14,5,5));
		graph[14].add(new Edge(14,13,5));
		
		graph[15].add(new Edge(15,14,5));
		graph[15].add(new Edge(15,16,5));
		
		graph[16].add(new Edge(16,7,5));
		graph[16].add(new Edge(16,15,5));
		graph[16].add(new Edge(16,6,5));
		
		graph[17].add(new Edge(17,7,5));
		
		graph[18].add(new Edge(18,8,5));
		graph[18].add(new Edge(18,19,5));
		graph[18].add(new Edge(18,3,5));
		graph[18].add(new Edge(18,20,5));
		
		graph[19].add(new Edge(19,18,5));
		graph[19].add(new Edge(19,8,5));
		graph[19].add(new Edge(19,3,5));
		graph[19].add(new Edge(19,20,5));
		
		graph[20].add(new Edge(20,19,5));
		graph[20].add(new Edge(20,18,5));
		graph[20].add(new Edge(20,8,5));
		graph[20].add(new Edge(20,3,5));
		
		graph[21].add(new Edge(21,22,5));
		
		graph[22].add(new Edge(22,21,5));
		graph[22].add(new Edge(22,2,5));
		graph[22].add(new Edge(22,3,5));
	}
	
	
	
	public static class Pair implements Comparable<Pair>{
		int node;
		int dist;
		
		public Pair(int n,int d) {
			this.node=n;
			this.dist=d;
		}
		@Override
		public int compareTo(Pair p2) {
			return this.dist-p2.dist;
		}
	}
	
	
	
	public static void dijkstra(ArrayList<Edge> graph[],int src,int dest,int n,ArrayList<String> stop) {
		PriorityQueue<Pair> pq=new PriorityQueue<>();
		int dist[]=new int[n];
		boolean vis[]=new boolean[n];
		for(int i=0;i<n;i++) {
			if(i!=src) {
				dist[i]=Integer.MAX_VALUE;
			}
		}
		pq.add(new Pair(src,0));
		while(!pq.isEmpty()) {
			Pair curr=pq.remove();
			vis[curr.node]=true;
			for(int i=0;i<graph[curr.node].size();i++) {
				Edge e=graph[curr.node].get(i);
				int v=e.dest;
				int u=e.src;
				if(dist[u]+e.wt<dist[v]) {
					dist[v]=dist[u]+e.wt;
					pq.add(new Pair(v,dist[v]));
				}
				
			}
		}
		System.out.print("The minimum fare from "+stop.get(src)+" to "+stop.get(dest)+" is "+dist[dest]+"Rs.");
		ArrayList<Integer> path=new ArrayList<>();
		int curr=dest;
		while(curr!=src) {
			path.add(curr);
			int next=-1;
			int MinDist=Integer.MAX_VALUE;
			for(int i=0;i<graph[curr].size();i++) {
				Edge e=graph[curr].get(i);
				if(dist[e.dest]<MinDist) {
					MinDist=dist[e.dest];
					next=e.dest;
				}
			}
			curr=next;
		}
		path.add(src);
		Collections.reverse(path);
		System.out.print("\nThe shortest route is ");
		for(int i=0;i<path.size();i++) {
			System.out.print(stop.get(path.get(i))+" ");
			if(i!=path.size()-1) {
				System.out.print(" --> ");
			}
		}
		System.out.println(" ");
		
	}
	
	public static void display(ArrayList<String> stop) {
		for(int i=0;i<stop.size();i++) {
			System.out.println(i+1+"."+stop.get(i));
		}
	}
	
	public static void menu(ArrayList<Edge> graph[],ArrayList<String> stop) {
		System.out.println("CHOOSE THE FOLLOWING OPTION");
		System.out.println("\t1.To display all the bus stops");
		System.out.println("\t2.To get the minimum fare from source bus stop to destination bus stop");
		System.out.println("\t3.Exit the menu");
		try (Scanner sc = new Scanner(System.in)) {
			int a=sc.nextInt();
			switch(a) {
			case 1:
				display(stop);
				menu(graph,stop);
				break;
			case 2:
				display(stop);
				System.out.println("Enter the SI NO. of the source bus stop ");
				int s=sc.nextInt();
				System.out.println("Enter the SI NO. of the destination bus stop ");
				int d=sc.nextInt();
				dijkstra(graph,s-1,d-1,23,stop);
				menu(graph,stop);
				break;
			case 3:
				System.out.print("\t********\tTHANK YOU\t********");
				break;
			default:
				System.out.println("Sorry error occured... TRY AGAIN");
				menu(graph,stop);
				
			}
		}
			
	}
	
	public static void main(String[] args) {
		ArrayList<String> stop=new ArrayList<String>();
		stop.add("Master Canteen Square");
		stop.add("Raj Mahal Square");
		stop.add("Biju Patnaik International Airport");
		stop.add("Khurdha new bus stand");
		stop.add("Chakeisiani");
		stop.add("Mancheswar railway station");
		stop.add("CRPF square");
		stop.add("Fire station Square");
		stop.add("Baramunda ISBT");
		stop.add("AG Square");
		stop.add("SAI Mandir");
		stop.add("Ravi Talkies square");
		stop.add("Kalpana Square");
		stop.add("Rasulagarh Square");
		stop.add("Vani Vihar Square");
		stop.add("Acharya Vihar Square");
		stop.add("Jaydev Vihar Square");
		stop.add("Sum Hospital");
		stop.add("Kalinga Nagar");
		stop.add("Kalinga Vihar");
		stop.add("AIIMS");
		stop.add("Ranasinghpur");
		stop.add("Khandagiri Square");
		int v=23;
		ArrayList<Edge> graph[]=new ArrayList[v];
		creategraph(graph);
		System.out.println("*********\tWELCOME TO MOBUS APP\t********* ");
		menu(graph,stop);
	}
}