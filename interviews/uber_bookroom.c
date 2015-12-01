/*
http://www.careercup.com/question?id=6262661043978240

Book a meeting from 2-6pm from different rooms.
Case #1:

S = {(1, 2), (2, 3), (3, 4), (4, 5), (5, 6), (6, 7), (7, 8), (8, 9), (9, 10), (10, 11)}
R = (1, 11);
while 10 x for 2 = 20. 2n => n

Case #2:
S = {(1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9), (1, 10), (1, 11)}
R = (1, 11);
while 1 x for 10 = 10. n

Case #3:
S = {(1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (6, 7), (6, 8), (6, 9), (6, 10), (6, 11)}
R = (1, 11);
1st while: 6 + 2nd while 4 = 10, n

c++, greedy, O(n log n), traversal = O(n)
sort: O(n log n), greedy: O(n)

*/

struct Compare {
    bool operator() (const pair<int, int>& a, const pair<int, int>& b) {
	if (a.first == b.first) return a.second < b.second;
	return a.first < b.first;
    }
};

vector<pair<int, int>> smallestSetOfRanges(vector<pair<int, int>>& ranges, pair<int, int> R) {
    vector<pair<int, int>> result;
    int target, i, maxTarget, maxIndex;
    bool match;

    if (R.first == R.second) {
	for (i = 0; i < ranges.size(); i++) {
	    if (ranges[i].first <= R.first && ranges[i].second >= R.second) {
		result.push_back(ranges[i]);
		break;
	    }
	}
	return result;
    }

    sort(ranges.begin(), ranges.end(), Compare());

    target = R.first;
    maxIndex = 0;
    while (target < R.second) {
	match = false;
	maxTarget = target;
	for (i = maxIndex; i < ranges.size(); i++) {
	    if (ranges[i].first <= target && ranges[i].second >= maxTarget) {
		match = true;
		maxTarget = ranges[i].second;
		maxIndex = i;
	    } else if (ranges[i].first > target) {
		break;
	    }
	}
	if (match == false) {
	    result.clear();
	    break;
	}
	result.push_back(ranges[maxIndex]);
	target = maxTarget;
	maxIndex++;
    }

    return result;
}
