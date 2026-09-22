# Developer Portfolio

GitHub Pages 기반의 개인 개발자 포트폴리오입니다. 프로젝트의 문제, 설계, 구현 과정을 중심으로 구성한 정적 웹사이트입니다.

## Projects

- Resume Builder
- PayLens

## Tech

- HTML
- CSS
- JavaScript

## Local Preview

`index.html` 파일을 브라우저에서 직접 열거나, 간단한 로컬 HTTP 서버를 실행해 확인할 수 있습니다.

```bash
python3 -m http.server 8000
```

그런 다음 브라우저에서 `http://localhost:8000`을 엽니다.

## GitHub Pages Deployment

1. GitHub 저장소의 **Settings**로 이동합니다.
2. **Pages** 메뉴를 엽니다.
3. **Build and deployment**에서 **Deploy from a branch**를 선택합니다.
4. 브랜치는 `main`, 폴더는 `/(root)`를 선택하고 저장합니다.
5. 배포가 완료되면 안내된 GitHub Pages URL에서 사이트를 확인합니다.

모든 페이지와 에셋은 상대 경로를 사용하므로 project site와 user site 모두에서 동작합니다.
