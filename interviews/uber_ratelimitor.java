float rate = 50.0; // unit: messages
float per  = 60.0; // unit: seconds
float quota = (float)rate; // unit: messages
last_check = now(); // floating-point, e.g. usec accuracy. Unit: seconds

void onRequestReceived(req) {
    current = now();
    time_passed = current - last_check;
    last_check = current;

    quota += time_passed * (rate / per);
    if (quota > rate) {
	quota = rate; // throttle
    }
    if (quota < 1.0) {
	discardRequest(req);
    } else {
	forwardRequest(req);
	allowance -= 1.0;
    }
}