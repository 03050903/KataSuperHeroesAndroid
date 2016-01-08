/*
 * Copyright (C) 2015 Karumi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.katasuperheroes.ui.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import com.github.katasuperheroes.model.SuperHero;
import com.github.katasuperheroes.model.SuperHeroesRepository;
import com.github.katasuperheroes.ui.presenter.SuperHeroesPresenter;
import com.github.katasuperheroes.usecase.GetSuperHeroes;
import github.com.katasuperheroes.R;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SuperHeroesPresenter.View {

  private SuperHeroesAdapter adapter;
  private SuperHeroesPresenter presenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main_activity);
    initializeToolbar();
    initializeAdapter();
    initializeRecyclerView();
    initializePresenter();
  }

  @Override public void showSuperHeroes(List<SuperHero> superHeroes) {
    adapter.addAll(superHeroes);
    adapter.notifyDataSetChanged();
  }

  @Override public void showLoading() {

  }

  @Override public void hideLoading() {

  }

  @Override public void showEmptyCase() {

  }

  @Override public void hideEmptyCase() {

  }

  private void initializeToolbar() {
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
  }

  private void initializeAdapter() {
    adapter = new SuperHeroesAdapter();
  }

  private void initializeRecyclerView() {
    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setHasFixedSize(true);
    recyclerView.setAdapter(adapter);
  }

  private void initializePresenter() {
    SuperHeroesRepository repository = new SuperHeroesRepository();
    GetSuperHeroes getSuperHeroes = new GetSuperHeroes(repository);
    presenter = new SuperHeroesPresenter(getSuperHeroes);
    presenter.setView(this);
    presenter.initialize();
  }
}
