# M324-Projekt

**Modul**: 324  
**Autor:innen**: Natascha Blumer, Roberto Marchese, Sascha Ritter  
**Datum**: 16.05.2026

---

## Build Pipeline

Für das Projekt wurde eine GitHub-Actions-Pipeline eingerichtet.  
Die Pipeline wird bei jedem Pull Request auf den `main`-Branch automatisch gestartet.

Die Pipeline führt zwei Build-Schritte aus:

1. **Frontend-Build**
   - verwendet Node.js
   - installiert die React-Abhängigkeiten
   - erstellt mit `npm run build` die produktiven HTML-, JavaScript- und CSS-Dateien

2. **Backend-Build**
   - verwendet Java
   - baut das Spring-Boot-Backend mit Maven
   - erzeugt im `target`-Ordner das Build-Artefakt

Dadurch wird bei jedem Pull Request geprüft, ob Frontend und Backend erfolgreich gebaut werden können.
