# Logger

Created because existing logging APIs are too verbose, requiring you to type the entire name of the log level to invoke its logging function - it is my conviction that the log levels of INFO, DEBUG, WARNING and ERROR are so ubiquitous and recognisable that these functions can be abbreviated to i(), d(), w() and e(). 

Additionally, the need for a message log level ALWAYS that ignores any instruction to be SILENT and logs regardless of the active log level has always stood out to me, for those situations when an important message must be delivered to the user, or when a hitherto unexpected error that should always be logged for troubleshooting reasons occurs.

## Requires

	- https://github.com/Bjonnfesk/Linguistics

## Changelog

+ **0.2.6.1:**
	
	- Added logLevel ALWAYS, which logs the message no matter what the Logger's log level is. Use sparingly.
		-Also added corresponding a() function, which logs at this log level.
	- Re-added outputStartMessage() and the call to it.
	- Corrected inconsistencies.
	- New default format.
+ **0.2.6:**

	- Added README.
	- Added support for calling the error logging function **Logger.e()** with an Exception instead of a text message. The Logger will then log the Exception's class' SimpleName, a colon and a space, and the Exception's Message. The other logging functions may also receive this functionality.
+ **0.2.5:** 

	- Initial Git version.
