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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.github.katasuperheroes.model.SuperHero;
import com.github.katasuperheroes.model.SuperHeroesRepository;
import com.github.katasuperheroes.ui.presenter.SuperHeroesPresenter;
import com.github.katasuperheroes.usecase.GetSuperHeroes;
import github.com.katasuperheroes.R;
import java.util.List;

public class MainActivity extends BaseActivity implements SuperHeroesPresenter.View {

  private SuperHeroesAdapter adapter;
  private SuperHeroesPresenter presenter;
  private View emptyCaseView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initializePresenter();
    initializeEmptyCaseView();
    initializeAdapter();
    initializeRecyclerView();
    presenter.initialize();
  }

  @Override public int getLayoutId() {
    return R.layout.main_activity;
  }

  @Override public void showSuperHeroes(List<SuperHero> superHeroes) {
    adapter.addAll(superHeroes);
    adapter.notifyDataSetChanged();
  }

  @Override public void openSuperHeroScreen(SuperHero superHero) {
    SuperHeroDetailActivity.open(this, superHero.getName());
  }

  @Override public void showEmptyCase() {
    emptyCaseView.setVisibility(View.VISIBLE);
  }

  @Override public void hideEmptyCase() {
    emptyCaseView.setVisibility(View.GONE);
  }

  private void initializeEmptyCaseView() {
    emptyCaseView = findViewById(R.id.tv_empty_case);
  }

  private void initializePresenter() {
    SuperHeroesRepository repository = new SuperHeroesRepository();
    GetSuperHeroes getSuperHeroes = new GetSuperHeroes(repository);
    presenter = new SuperHeroesPresenter(getSuperHeroes);
    presenter.setView(this);
  }

  private void initializeAdapter() {
    adapter = new SuperHeroesAdapter(presenter);
  }

  private void initializeRecyclerView() {
    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setHasFixedSize(true);
    recyclerView.setAdapter(adapter);
  }
}
