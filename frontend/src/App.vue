<template>
  <div class="app">
    <header>
      <h1>Student Management</h1>
      <!-- RouterLink changes pages without reloading the whole app. -->
      <nav>
        <RouterLink to="/students">Students</RouterLink>
        <RouterLink to="/about">About</RouterLink>
      </nav>
    </header>

    <!-- The current page appears here. -->
    <RouterView />
  </div>
</template>

<style>
body { margin: 0; font-family: Arial, sans-serif; background: #f4f6fa; color: #20283a; }
.app { max-width: 900px; margin: 30px auto; padding: 0 16px; }
header { display: flex; justify-content: space-between; align-items: center; gap: 16px; flex-wrap: wrap; }
nav { display: flex; gap: 14px; }
a { color: #4f46e5; }
a.router-link-active { font-weight: bold; }
.panel { background: white; padding: 18px; margin: 18px 0; border-radius: 10px; }
input { padding: 9px; margin: 4px; }
button { padding: 8px 12px; margin: 3px; cursor: pointer; }
table { width: 100%; border-collapse: collapse; background: white; }
td, th { padding: 10px; border: 1px solid #ddd; text-align: left; }
.error { color: #b00020; }
</style>
