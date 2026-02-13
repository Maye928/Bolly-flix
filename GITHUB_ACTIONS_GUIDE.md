# Guide de Compilation GitHub Actions

Ce projet est maintenant configuré pour être compilé automatiquement via GitHub Actions.

## 1. Structure ajoutée
- `.github/workflows/android_build.yml` : Le script qui gère la compilation automatique.
- Modification de `app/build.gradle` : Ajout du support pour la signature via variables d'environnement.

## 2. Comment utiliser
1. Poussez ce projet sur un dépôt GitHub.
2. Allez dans l'onglet **Actions** de votre dépôt pour voir la compilation en cours.
3. Une fois terminée, l'APK sera disponible dans les **Artifacts** de l'exécution.

## 3. Configuration de la version Release (Signature)
Pour générer un APK signé prêt pour la production, vous devez ajouter les **Secrets** suivants dans les paramètres de votre dépôt GitHub (`Settings > Secrets and variables > Actions`) :

| Secret | Description |
| :--- | :--- |
| `SIGNING_KEY_STORE_BASE64` | Votre fichier .jks converti en Base64 |
| `SIGNING_KEY_ALIAS` | L'alias de votre clé |
| `SIGNING_KEY_PASSWORD` | Le mot de passe de la clé |
| `SIGNING_STORE_PASSWORD` | Le mot de passe du keystore |

> **Note** : Le workflow actuel générera toujours une version **Debug** fonctionnelle même sans ces secrets.
