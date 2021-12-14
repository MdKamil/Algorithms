package algorithm.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountsMerge {

	public static List<List<String>> accountsMerge(List<List<String>> accounts) {
		HashMap<String, Integer> emailMap = new HashMap<>();
		HashMap<Integer, List<String>> accountMap = new HashMap<>();
		for (int account = 0; account <= accounts.size() - 1; ++account) {
			int emailAccount = account;
			int accountToMap = account;
			if (emailMap.containsKey(accounts.get(account).get(1))) {
				accountToMap = emailMap.get(accounts.get(account).get(1));
			}
			for (int idx = 1; idx <= accounts.get(account).size() - 1; ++idx) {
				String email = accounts.get(account).get(idx);
				emailAccount = emailMap.containsKey(email) ? emailMap.get(email) : account;
				if (accountToMap != emailAccount) {
					if (!emailMap.containsKey(email)) {
						emailMap.put(email, accountToMap);
						accountMap.get(accountToMap).add(email);
					} else {
						if (accountMap.get(accountToMap).size() >= accountMap.get(emailAccount).size()) {
							List<String> emails = accountMap.get(emailAccount);
							for (int idx1 = 1; idx1 <= emails.size() - 1; ++idx1) {
								emailMap.put(emails.get(idx1), accountToMap);
								accountMap.get(accountToMap).add(emails.get(idx1));
							}
							accountMap.remove(emailAccount);
						} else {
							List<String> emails = accountMap.get(accountToMap);
							for (int idx1 = 1; idx1 <= emails.size() - 1; ++idx1) {
								emailMap.put(emails.get(idx1), emailAccount);
								accountMap.get(emailAccount).add(emails.get(idx1));
							}
							accountMap.remove(accountToMap);
							accountToMap = emailAccount;
						}
					}
				} else {
					if (accountMap.containsKey(accountToMap)) {
						if (!emailMap.containsKey(email)) {
							accountMap.get(accountToMap).add(email);
						}
					} else {
						List<String> list = new ArrayList<>();
						list.add(accounts.get(accountToMap).get(0));
						list.add(email);
						accountMap.put(accountToMap, list);
					}
					emailMap.put(email, accountToMap);
					accountToMap = emailAccount;
				}
			}
		}
		List<List<String>> uniqueAccounts = new ArrayList<>();
		for (Map.Entry<Integer, List<String>> account : accountMap.entrySet()) {
			List<String> accountList = account.getValue();
			Collections.sort(accountList.subList(1, accountList.size()));
			uniqueAccounts.add(accountList);
		}
		return uniqueAccounts;
	}

	public static void main(String[] args) {
		List<List<String>> accounts = new ArrayList<List<String>>();
		List<String> list = new ArrayList<String>();
		list.add("David");
		list.add("David0@m.co");
		list.add("David4@m.co");
		list.add("David3@m.co");
		accounts.add(list);

		list = new ArrayList<String>();
		list.add("David");
		list.add("David5@m.co");
		list.add("David5@m.co");
		list.add("David0@m.co");
		accounts.add(list);

		list = new ArrayList<String>();
		list.add("David");
		list.add("David1@m.co");
		list.add("David4@m.co");
		list.add("David0@m.co");
		accounts.add(list);

		list = new ArrayList<String>();
		list.add("David");
		list.add("David0@m.co");
		list.add("David1@m.co");
		list.add("David3@m.co");
		accounts.add(list);

		list = new ArrayList<String>();
		list.add("David");
		list.add("David4@m.co");
		list.add("David1@m.co");
		list.add("David3@m.co");
		accounts.add(list);

		List<List<String>> uniqueAccounts = accountsMerge(accounts);
		for (List<String> l : uniqueAccounts) {
			System.out.println(l);
		}

	}
}
