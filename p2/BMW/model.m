BMW_Series2 : Body DriveTrain Fuel MSRP HorsePower FuelEfficiency :: _BMW_Series2 ;

Body : Sedan
	| Coupe
	| GranCoupe
	| Convertible
	| SportsActivityVehicle
	| Roadster
	| SportsActivityCoupe
	| SportsWagon
	| GranTurism ;

DriveTrain : xDrive
	| RearWheel ;

Fuel : Hybrid
	| Gasoline
	| Electric
	| Diesel ;

MSRP : $32100
	| $33900
	| $43100
	| $44900 ;

HorsePower : HP240
	| HP320 ;

FuelEfficiency : MPG30
	| MPG32
	| MPG35
	| MPG36 ;

