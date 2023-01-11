# TVZ AWK Delta App
Diese App hat zum Ziel die Anwesenheiten der Turnerinnen und Tunrer zu erfassen. Dabei soll die Erfassung auf den Anmeldungen
aus einem Doodle ähnlichen Tool (aktuell Xoyondo) passieren. Dazu wird automatisch ein CSV File von Xoyondo heruntergeladen, 
geparst und in der App visualisiert. Die Anwesenheiten können dann per Antippen erfasst werden und werden dann auf Google Firestore
persistiert.

# Roadmap
- [x] CSV Download
- [x] CSV Parser
- [x] Visualisierung der An- und Abmeldungen
- [x] Erfassen der effektiven Anwesenheit
- [ ] Persistierung der Daten in Google Firestore
- [ ] App Icon
- [ ] Dynamische Xoyondo Quelle (mit Persistierung des Xoyondo Links auf dem Gerät)
- [ ] Anheigen "historischer" Daten (Termine die im aktuell laufenden Xoyondo Poll nicht mehr ersichtlich sind
