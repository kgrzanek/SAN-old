import './app.css';
import App from './App.svelte';

class Panic extends Error {}

function panic<T>(message = 'PANIC'): T {
  throw new Panic(message);
}

const app = new App({
  target: document.getElementById('app') ?? panic('#app not found'),
});

export { app, type Panic };
