# Bollyflix - Application Android

## 🚀 Compilation automatique via GitHub Actions

### Étapes :
1. Créez un nouveau dépôt sur GitHub
2. Uploadez tout le contenu de ce ZIP dans le dépôt
3. Allez dans l'onglet **Actions** du dépôt
4. Le workflow **Build APK** se lance automatiquement
5. Une fois terminé, téléchargez l'APK depuis les **Artifacts**

### Compilation manuelle :
```bash
./gradlew assembleDebug
```
L'APK sera dans `app/build/outputs/apk/debug/`

## 📱 Fonctionnalités
- Authentification (login/signup)
- Navigation : Films, Séries, Web-séries, Dessins animés, Live TV
- Lecteur vidéo ExoPlayer (HLS supporté)
- Interface Material Design sombre
- API Bollyflix préconfigurée
