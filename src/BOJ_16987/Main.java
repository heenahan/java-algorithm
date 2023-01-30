package BOJ_16987;

import java.util.Arrays;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Egg {
	private int durability;
	private int weight;
	
	public Egg(int durability, int weight) {
		this.durability = durability;
		this.weight = weight;
	}
	
	public int getDurability() {
		return durability;
	}
	
	public int getWeight() {
		return weight;
	}
	
}

public class Main {
	
	static int n;
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		Egg[] eggs = new Egg[n];
		boolean[] broken = new boolean[n];
		for (int i = 0; i < n; i++) {
			String[] input = br.readLine().split(" ");
			int durability = Integer.parseInt(input[0]);
			int weight = Integer.parseInt(input[1]);
			eggs[i] = new Egg(durability, weight);
		}
		
		int result = T.solution(0, eggs, broken);
		if (result != Integer.MIN_VALUE) System.out.println(result);
		else System.out.println(0);
		
		br.close();
	}
	
	// egg : 들고 있는 계란
	public int solution(int egg, Egg[] eggs, boolean[] broken) {
		if (egg == n) { // 깨진 계란 세기
			int sum = 0;
			for (boolean b : broken) {
				if (b) sum++;
			}
			return sum;
		}
		Egg hold = eggs[egg]; // 들고 있는 계란
		int holdWeight = hold.getWeight();
		int holdDurability = hold.getDurability();
		boolean flag = false;
		int result = Integer.MIN_VALUE;
		if (broken[egg]) result = Math.max(result, solution(egg + 1, eggs, broken)); // 들고 있는 계란이 깨졌으면 종료
		else {
			for (int i = 0; i < n; i++) {
				if (broken[i] || i == egg) continue; // 깨진 계란이거나 들고 있는 계란이면 pass
				Egg[] nextE = Arrays.stream(eggs).toArray(Egg[]::new); // 복사
				boolean[] nextB = new boolean[n];
				
				for (int j = 0; j < n; j++) nextB[j] = broken[j];
				flag = true;
				
				Egg hit = eggs[i];
				int hitWeight = hit.getWeight();
				int hitDurability = hit.getDurability();
				
				nextE[egg] = new Egg(holdDurability - hitWeight, holdWeight);
				nextE[i] = new Egg(hitDurability - holdWeight, hitWeight);
				
				// 잡은 달걀과 친 달걀이 부러졌으면 true
				if (nextE[egg].getDurability() <= 0) nextB[egg] = true;
				if (nextE[i].getDurability() <= 0) nextB[i] = true;
				
				// 안깨진 계란 중 하나를 깼으면 다음 계란 잡기
				result = Math.max(result, solution(egg + 1, nextE, nextB));
			}
		}
		// 자신을 제외한 모든 달걀 깨졌으면
		if (!flag) result = Math.max(result, solution(egg + 1, eggs, broken));
		return result;
	}
}
