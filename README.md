# Logger

## Changelog

+ **0.2.6.1:**
	
	- Added logLevel ALWAYS, which logs the message no matter what the Logger's log level is. Use sparingly.
	- Re-added outputStartMessage() and the call to it.
+ **0.2.6:**

	- Added README.
	- Added support for calling the error logging function **Logger.e()** with an Exception instead of a text message. The Logger will then log the Exception's class' SimpleName, a colon and a space, and the Exception's Message. The other logging functions may also receive this functionality.
+ **0.2.5:** 

	- Initial Git version.
