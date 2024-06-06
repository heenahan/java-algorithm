package PGS_불량_사용자;

import java.util.*;

public class Solution {

    Set<Set<String>> bannedIds = new HashSet<>();
    String[] matches;
    boolean[] visited;

    public int solution(String[] user_id, String[] banned_id) {
        matches = new String[banned_id.length];
        visited = new boolean[user_id.length];

        backTracking(0, user_id, banned_id);
        return bannedIds.size();
    }

    // 깊이, 불량 사용자, 매칭된 응모자
    public void backTracking(int depth, String[] user_id, String[] banned_id) {
        if (depth == banned_id.length) {
            Set<String> set = new HashSet<>();
            for (String match : matches) {
                set.add(match);
            }
            bannedIds.add(set);
            return;
        }

        String banId = banned_id[depth];
        for (int i = 0; i < user_id.length; i++) {
            if (visited[i]) {
                continue;
            }
            String userId = user_id[i];
            // 길이 같지 않으면 pass
            if (banId.length() != userId.length()) {
                continue;
            }
            // 매칭 여부 판단
            String[] user = userId.split("");
            String[] ban = banId.split("");
            boolean isMatched = true;
            for (int j = 0; j < banId.length(); j++) {
                if (ban[j].equals("*")) {
                    continue;
                }
                if (!ban[j].equals(user[j])) {
                    isMatched = false;
                    break;
                }
            }
            // 매칭시
            if (isMatched) {
                visited[i] = true;
                matches[depth] = userId;
                backTracking(depth + 1, user_id, banned_id);
                visited[i] = false;
            }
        }
    }
}
