from operator import itemgetter

def printConfiguration(config):
	proposal = str(config.get('Connection', 'user'))
	password = str(config.get('Connection', 'password'))
	url = str(config.get('Connection', 'url'))

	print("\n\n-------------------")
	print("Configuration Values")
	print("-------------------")
	print("proposal   %s" % str(proposal))
	print("password   %s" % str(password))
	print("URL        %s\n" % str(url))

def printify(value):
	return str(value).ljust(22, ' ')


	
def printExperiment(experiment):
	print("\n\n-------------------")
	print("Experiment")
	print("-------------------")
	print("Name     %s" % str(experiment['name']))
	print("ID       %s" % str(experiment['experimentId']))
	print("Date     %s" % str(experiment['creationDate']))	 
